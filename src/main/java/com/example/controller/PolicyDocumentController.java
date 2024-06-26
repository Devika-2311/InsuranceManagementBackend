package com.example.controller;

import com.example.model.PolicyDocument;
import com.example.model.PolicyType;
import com.example.model.UserPolicy;
import com.example.service.PolicyDocumentService;
import com.example.service.UserPolicyService;
import com.example.exp.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3003", allowCredentials = "true")
@RestController
@RequestMapping("/policy-documents")
public class PolicyDocumentController {

    @Value("${react.public.folder}")
    private String reactPublicFolder;

    @Value("${assets.images.path}")
    private String assetsImagesPath;

    private final PolicyDocumentService policyDocumentService;
    private final UserPolicyService userPolicyService;

    static final String INVALID_USER_POLICY_ID = "Invalid user policy ID";

    public PolicyDocumentController(PolicyDocumentService policyDocumentService, UserPolicyService userPolicyService) {
        this.policyDocumentService = policyDocumentService;
        this.userPolicyService = userPolicyService;
    }

    @PostMapping("/create/term")
    public ResponseEntity<Object> createTermPolicyDocument(
            @RequestParam("policyType") PolicyType policyType,
            @RequestParam("annualIncome") Double annualIncome,
            @RequestParam("anyDisease") Boolean anyDisease,
            @RequestParam("nomineeName") String nomineeName,
            @RequestParam("nomineeRelation") String nomineeRelation,
            @RequestParam("nomineeEmail") String nomineeEmail,
            @RequestParam("nomineeProof") MultipartFile nomineeProof,
            @RequestParam("userPolicyId") Long userPolicyId) {
        return createPolicyDocument(policyType, nomineeProof, userPolicyId, policyDocument -> {
            policyDocument.setAnnualIncome(annualIncome);
            policyDocument.setAnyDisease(anyDisease);
            policyDocument.setNomineeName(nomineeName);
            policyDocument.setNomineeRelation(nomineeRelation);
            policyDocument.setNomineeEmail(nomineeEmail);
            policyDocument.setNomineeProof(assetsImagesPath + nomineeProof.getOriginalFilename());
        });
    }

    @PostMapping("/create/auto")
    public ResponseEntity<Object> createAutoPolicyDocument(
            @RequestParam("policyType") PolicyType policyType,
            @RequestParam("vehicleModelNo") String vehicleModelNo,
            @RequestParam("licensePlateNo") String licensePlateNo,
            @RequestParam("vehicleValue") Double vehicleValue,
            @RequestParam("primaryUse") String primaryUse,
            @RequestParam("vehicleType") String vehicleType,
            @RequestParam("driverAge") Integer driverAge,
            @RequestParam("cheatSheet") MultipartFile cheatSheet,
            @RequestParam("userPolicyId") Long userPolicyId) {
        return createPolicyDocument(policyType, cheatSheet, userPolicyId, policyDocument -> {
            policyDocument.setVehicleModelNo(vehicleModelNo);
            policyDocument.setLicensePlateNo(licensePlateNo);
            policyDocument.setVehicleValue(vehicleValue);
            policyDocument.setPrimaryUse(primaryUse);
            policyDocument.setVehicleType(vehicleType);
            policyDocument.setDriverAge(driverAge);
            policyDocument.setCheatSheet(assetsImagesPath + cheatSheet.getOriginalFilename());
        });
    }

    @PostMapping("/create/health")
    public ResponseEntity<Object> createHealthPolicyDocument(
            @RequestParam("policyType") PolicyType policyType,
            @RequestParam("age") Integer age,
            @RequestParam("height") Double height,
            @RequestParam("weight") Double weight,
            @RequestParam("smoke") Boolean smoke,
            @RequestParam("alcohol") Boolean alcohol,
            @RequestParam("bp") Boolean bp,
            @RequestParam("diabetics") Boolean diabetics,
            @RequestParam("criticalDisease") String criticalDisease,
            @RequestParam("healthReport") MultipartFile healthReport,
            @RequestParam("userPolicyId") Long userPolicyId) {
        return createPolicyDocument(policyType, healthReport, userPolicyId, policyDocument -> {
            policyDocument.setAge(age);
            policyDocument.setHeight(height);
            policyDocument.setWeight(weight);
            policyDocument.setSmoke(smoke);
            policyDocument.setAlcohol(alcohol);
            policyDocument.setBp(bp);
            policyDocument.setDiabetics(diabetics);
            policyDocument.setCriticalDisease(criticalDisease);
            policyDocument.setHealthReport(assetsImagesPath + healthReport.getOriginalFilename());
        });
    }

    private ResponseEntity<Object> createPolicyDocument(PolicyType policyType, MultipartFile file, Long userPolicyId, PolicyDocumentConsumer consumer) {
        if (file.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            String fileName = file.getOriginalFilename();
            Path uploadPath = Paths.get(reactPublicFolder + assetsImagesPath);
            if (fileName == null || fileName.isEmpty()) {
                throw new IllegalArgumentException("File name cannot be null or empty");
            }

            int counter = 1;
            while (Files.exists(uploadPath.resolve(fileName))) {
                String[] parts = fileName.split("\\.");
                if (parts.length > 1) {
                    fileName = parts[0] + "_" + counter + "." + parts[1];
                } else {
                    fileName = parts[0] + "_" + counter;
                }
                counter++;
            }
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath);

            PolicyDocument policyDocument = new PolicyDocument();
            policyDocument.setPolicyType(policyType);
            consumer.accept(policyDocument);
            if (policyType == PolicyType.HEALTH) {
                policyDocument.setHealthReport(assetsImagesPath + fileName);
            } else if (policyType == PolicyType.AUTO) {
                policyDocument.setCheatSheet(assetsImagesPath + fileName);
            } else {
                policyDocument.setNomineeProof(assetsImagesPath + fileName);
            }

            Optional<UserPolicy> userPolicyOptional = Optional.ofNullable(userPolicyService.getUserPolicyByUserPolicyId(userPolicyId));
            if (userPolicyOptional.isPresent()) {
                policyDocument.setUserPolicy(userPolicyOptional.get());
            } else {
                throw new ResourceNotFoundException(INVALID_USER_POLICY_ID);
            }

            PolicyDocument createdPolicyDocument = policyDocumentService.createPolicyDocument(policyDocument);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPolicyDocument);

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @FunctionalInterface
    public interface PolicyDocumentConsumer {
        void accept(PolicyDocument policyDocument);
    }

    @GetMapping("/get")
    public ResponseEntity<List<PolicyDocument>> getAllPolicyDocuments() {
        List<PolicyDocument> policyDocuments = policyDocumentService.getAllPolicyDocuments();
        return ResponseEntity.ok(policyDocuments);
    }

    @GetMapping("getById/{policyDetailsId}")
    public ResponseEntity<PolicyDocument> getPolicyDocumentById(@PathVariable Long policyDetailsId) {
        try {
            PolicyDocument policyDocument = policyDocumentService.getPolicyDocumentById(policyDetailsId);
            return ResponseEntity.ok(policyDocument);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
