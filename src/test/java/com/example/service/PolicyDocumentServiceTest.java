package com.example.service;

import com.example.exp.ResourceNotFoundException;
import com.example.model.PolicyDocument;
import com.example.repo.PolicyDocumentRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PolicyDocumentServiceTest {

    @Mock
    private PolicyDocumentRepo policyDocumentRepository;

    @InjectMocks
    private PolicyDocumentService policyDocumentService;

    @Test
    public void testCreatePolicyDocument() {
        PolicyDocument policyDocument = new PolicyDocument();
        policyDocument.setPolicyDetailsId(1L);

        when(policyDocumentRepository.save(policyDocument)).thenReturn(policyDocument);

        PolicyDocument createdPolicyDocument = policyDocumentService.createPolicyDocument(policyDocument);
        assertEquals(policyDocument, createdPolicyDocument);
        verify(policyDocumentRepository, times(1)).save(policyDocument);
    }

    @Test
    public void testGetAllPolicyDocuments() {
        PolicyDocument policyDocument1 = new PolicyDocument();
        policyDocument1.setPolicyDetailsId(1L);
        PolicyDocument policyDocument2 = new PolicyDocument();
        policyDocument2.setPolicyDetailsId(2L);

        when(policyDocumentRepository.findAll()).thenReturn(Arrays.asList(policyDocument1, policyDocument2));

        List<PolicyDocument> policyDocuments = policyDocumentService.getAllPolicyDocuments();
        assertEquals(2, policyDocuments.size());
        verify(policyDocumentRepository, times(1)).findAll();
    }

    @Test
    public void testGetPolicyDocumentById_PolicyFound() throws ResourceNotFoundException {
        PolicyDocument policyDocument = new PolicyDocument();
        policyDocument.setPolicyDetailsId(1L);

        when(policyDocumentRepository.findById(1L)).thenReturn(Optional.of(policyDocument));

        PolicyDocument foundPolicyDocument = policyDocumentService.getPolicyDocumentById(1L);
        assertEquals(policyDocument, foundPolicyDocument);
        verify(policyDocumentRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetPolicyDocumentById_PolicyNotFound() {
        when(policyDocumentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            policyDocumentService.getPolicyDocumentById(1L);
        });

        verify(policyDocumentRepository, times(1)).findById(1L);
    }
}
