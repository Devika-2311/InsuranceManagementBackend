package com.example.service;

import com.example.model.UserPolicy;
import com.example.repo.UserPolicyRepo;
import com.example.exp.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPolicyService {

    private final UserPolicyRepo userPolicyRepository;

    public UserPolicyService(UserPolicyRepo userPolicyRepository) {
        this.userPolicyRepository = userPolicyRepository;
    }

    public UserPolicy createUserPolicy(UserPolicy userPolicy) {
        return userPolicyRepository.save(userPolicy);
    }

    public List<UserPolicy> getAllUserPolicies() {
        return userPolicyRepository.findAll();
    }

    
    public List<UserPolicy> getUserPoliciesByUserId(Long userId) throws ResourceNotFoundException {
        List<UserPolicy> policies = userPolicyRepository.findAllByUser_UserId(userId);
        if (policies.isEmpty()) {
            throw new ResourceNotFoundException("User not found for this id :: " + userId);
        }
        return policies;
    }

    public UserPolicy getUserPolicyByUserPolicyId(Long userPolicyId) throws ResourceNotFoundException {
       
        return userPolicyRepository.findById(userPolicyId)
                .orElseThrow(() -> new ResourceNotFoundException("UserPolicy not found for this id :: " + userPolicyId));
    }
}
