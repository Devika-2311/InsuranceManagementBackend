package com.example.service;

import com.example.exp.ResourceNotFoundException;
import com.example.model.Policy;
import com.example.repo.PolicyRepo;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PolicyService {


	 private final PolicyRepo policyRepository;

	    public PolicyService(PolicyRepo policyRepository) {
	        this.policyRepository = policyRepository;
	    }

	    public Policy getPolicyById(Long policyId) throws ResourceNotFoundException {
	        return policyRepository.findById(policyId)
	            .orElseThrow(() -> new ResourceNotFoundException("Policy not found for this id :: " + policyId));
	    }

	    public List<Policy> getAllPolicies() {
	        return policyRepository.findAll();
	    }
}

