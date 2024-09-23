package com.example.controller;

import com.example.exp.ResourceNotFoundException;
import com.example.model.Policy;
import com.example.service.PolicyService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3003", allowCredentials = "true")

@RestController
@RequestMapping("/policies")
public class PolicyController {

	private final PolicyService policyService;

 
    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Policy>> getAllPolicies() {
        List<Policy> policies = policyService.getAllPolicies();
        return ResponseEntity.ok(policies);
    }

    @GetMapping("/{policyId}")
    public ResponseEntity<Policy> getPolicyById(@PathVariable Long policyId) {
        try {
            Policy policy = policyService.getPolicyById(policyId);
            System.out.println("hello policy");
            return ResponseEntity.ok(policy);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
