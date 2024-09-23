package com.example.controller;

import com.example.model.PolicyDocument;
import com.example.model.PolicyType;
import com.example.model.UserPolicy;
import com.example.service.PolicyDocumentService;
import com.example.service.UserPolicyService;
import com.example.exp.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PolicyDocumentControllerTest {

    @Mock
    private PolicyDocumentService policyDocumentService;

    @Mock
    private UserPolicyService userPolicyService;

    @InjectMocks
    private PolicyDocumentController policyDocumentController;

    @Test
    public void testCreateTermPolicyDocument() throws IOException, ResourceNotFoundException {
        PolicyDocument policyDocument = new PolicyDocument();
        UserPolicy userPolicy = new UserPolicy();
        userPolicy.setUserPolicyID(1L);
        policyDocument.setUserPolicy(userPolicy);

        MockMultipartFile nomineeProof = new MockMultipartFile("nomineeProof", "nomineeProof.txt", "text/plain", "data".getBytes());

        when(userPolicyService.getUserPolicyByUserPolicyId(1L)).thenReturn(userPolicy);
        when(policyDocumentService.createPolicyDocument(any(PolicyDocument.class))).thenReturn(policyDocument);

        ResponseEntity<Object> response = policyDocumentController.createTermPolicyDocument(
                PolicyType.TERM, 50000.0, true, "John Doe", "Spouse", "john.doe@example.com", nomineeProof, 1L);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(userPolicyService, times(1)).getUserPolicyByUserPolicyId(1L);
        verify(policyDocumentService, times(1)).createPolicyDocument(any(PolicyDocument.class));
    }

    @Test
    public void testCreateAutoPolicyDocument() throws IOException, ResourceNotFoundException {
        PolicyDocument policyDocument = new PolicyDocument();
        UserPolicy userPolicy = new UserPolicy();
        userPolicy.setUserPolicyID(1L);
        policyDocument.setUserPolicy(userPolicy);

        MockMultipartFile cheatSheet = new MockMultipartFile("cheatSheet", "cheatSheet.txt", "text/plain", "data".getBytes());

        when(userPolicyService.getUserPolicyByUserPolicyId(1L)).thenReturn(userPolicy);
        when(policyDocumentService.createPolicyDocument(any(PolicyDocument.class))).thenReturn(policyDocument);

        ResponseEntity<Object> response = policyDocumentController.createAutoPolicyDocument(
                PolicyType.AUTO, "Model X", "ABC123", 500000.0, "Personal", "SUV", 30, cheatSheet, 1L);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(userPolicyService, times(1)).getUserPolicyByUserPolicyId(1L);
        verify(policyDocumentService, times(1)).createPolicyDocument(any(PolicyDocument.class));
    }

    @Test
    public void testCreateHealthPolicyDocument() throws IOException, ResourceNotFoundException {
        PolicyDocument policyDocument = new PolicyDocument();
        UserPolicy userPolicy = new UserPolicy();
        userPolicy.setUserPolicyID(1L);
        policyDocument.setUserPolicy(userPolicy);

        MockMultipartFile healthReport = new MockMultipartFile("healthReport", "healthReport.txt", "text/plain", "data".getBytes());

        when(userPolicyService.getUserPolicyByUserPolicyId(1L)).thenReturn(userPolicy);
        when(policyDocumentService.createPolicyDocument(any(PolicyDocument.class))).thenReturn(policyDocument);

        ResponseEntity<Object> response = policyDocumentController.createHealthPolicyDocument(
                PolicyType.HEALTH, 30, 175.0, 70.0, true, true, true, true, "None", healthReport, 1L);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(userPolicyService, times(1)).getUserPolicyByUserPolicyId(1L);
        verify(policyDocumentService, times(1)).createPolicyDocument(any(PolicyDocument.class));
    }

    @Test
    public void testGetAllPolicyDocuments() {
        PolicyDocument policyDocument1 = new PolicyDocument();
        policyDocument1.setPolicyDetailsId(1L);
        PolicyDocument policyDocument2 = new PolicyDocument();
        policyDocument2.setPolicyDetailsId(2L);

        when(policyDocumentService.getAllPolicyDocuments()).thenReturn(Arrays.asList(policyDocument1, policyDocument2));

        ResponseEntity<List<PolicyDocument>> response = policyDocumentController.getAllPolicyDocuments();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        verify(policyDocumentService, times(1)).getAllPolicyDocuments();
    }

    @Test
    public void testGetPolicyDocumentById_PolicyFound() throws ResourceNotFoundException {
        PolicyDocument policyDocument = new PolicyDocument();
        policyDocument.setPolicyDetailsId(1L);

        when(policyDocumentService.getPolicyDocumentById(1L)).thenReturn(policyDocument);

        ResponseEntity<PolicyDocument> response = policyDocumentController.getPolicyDocumentById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(policyDocument, response.getBody());
        verify(policyDocumentService, times(1)).getPolicyDocumentById(1L);
    }

    @Test
    public void testGetPolicyDocumentById_PolicyNotFound() throws ResourceNotFoundException {
        when(policyDocumentService.getPolicyDocumentById(1L)).thenThrow(new ResourceNotFoundException("PolicyDocument not found"));

        ResponseEntity<PolicyDocument> response = policyDocumentController.getPolicyDocumentById(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(policyDocumentService, times(1)).getPolicyDocumentById(1L);
    }
}
