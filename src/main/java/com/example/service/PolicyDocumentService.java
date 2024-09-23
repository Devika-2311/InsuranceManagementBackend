package com.example.service;

import com.example.exp.ResourceNotFoundException;
import com.example.model.PolicyDocument;
import com.example.repo.PolicyDocumentRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyDocumentService {

	 private final PolicyDocumentRepo policyDocumentRepository;

	    public PolicyDocumentService(PolicyDocumentRepo policyDocumentRepo) {
	        this.policyDocumentRepository = policyDocumentRepo;
	    }

	    public PolicyDocument createPolicyDocument(PolicyDocument policyDocument) {
	        return policyDocumentRepository.save(policyDocument);
	    }

	    public List<PolicyDocument> getAllPolicyDocuments() {
	        return policyDocumentRepository.findAll();
	    }

	    public PolicyDocument getPolicyDocumentById(Long policyDetailsId) throws ResourceNotFoundException {
	        return policyDocumentRepository.findById(policyDetailsId)
	                .orElseThrow(() -> new ResourceNotFoundException("PolicyDocument not found for this user id :: " + policyDetailsId));
	    }
   
}
