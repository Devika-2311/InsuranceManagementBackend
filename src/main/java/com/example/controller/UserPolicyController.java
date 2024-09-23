package com.example.controller;

import com.example.model.UserPolicy;
import com.example.service.UserPolicyService;
import com.example.exp.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3001", allowCredentials = "true")
@RequestMapping("/user-policies")
public class UserPolicyController {

    private final UserPolicyService userPolicyService;

    public UserPolicyController(UserPolicyService userPolicyService) {
        this.userPolicyService = userPolicyService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createUserPolicy(@RequestBody UserPolicy userPolicy) {
        UserPolicy createdUserPolicy = userPolicyService.createUserPolicy(userPolicy);
        return ResponseEntity.ok(createdUserPolicy.getUserPolicyId());  
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserPolicy>> getPoliciesById(@PathVariable Long userId) {
        try {
			try {
			    List<UserPolicy> policies = userPolicyService.getUserPoliciesByUserId(userId);
			    if (policies.isEmpty()) {
			        throw new ResourceNotFoundException("No policies found for user id: " + userId);
			    }
			    return ResponseEntity.ok(policies);
			} catch (ResourceNotFoundException e) {
			    return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
    }

    @GetMapping("/get")
    public ResponseEntity<List<UserPolicy>> getAllUserPolicies() {
        List<UserPolicy> policies = userPolicyService.getAllUserPolicies();
        if (policies.isEmpty()) {
            return ResponseEntity.noContent().build();  
        }
        return ResponseEntity.ok(policies);
    }

    @GetMapping("/userpolicy/{userId}")
    public ResponseEntity<List<UserPolicy>> getUserPoliciesByUserId(@PathVariable Long userId) throws ResourceNotFoundException  {
        List<UserPolicy> policies = userPolicyService.getUserPoliciesByUserId(userId);
        if (policies.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(policies);
    }
}
