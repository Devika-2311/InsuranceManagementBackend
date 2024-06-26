package com.example.controller;

import com.example.exp.ResourceNotFoundException;
import com.example.model.Policy;
import com.example.service.PolicyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PolicyControllerTest {

    @Mock
    private PolicyService policyService;

    @InjectMocks
    private PolicyController policyController;

    @Test
    public void testGetAllPolicies() {
        Policy policy1 = new Policy();
        policy1.setPolicyId(1L);
        Policy policy2 = new Policy();
        policy2.setPolicyId(2L);

        when(policyService.getAllPolicies()).thenReturn(Arrays.asList(policy1, policy2));

        ResponseEntity<List<Policy>> response = policyController.getAllPolicies();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        verify(policyService, times(1)).getAllPolicies();
    }

    @Test
    public void testGetPolicyById_PolicyFound() throws ResourceNotFoundException {
        Policy policy = new Policy();
        policy.setPolicyId(1L);

        when(policyService.getPolicyById(1L)).thenReturn(policy);

        ResponseEntity<Policy> response = policyController.getPolicyById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(policy, response.getBody());
        verify(policyService, times(1)).getPolicyById(1L);
    }

    @Test
    public void testGetPolicyById_PolicyNotFound() throws ResourceNotFoundException {
        when(policyService.getPolicyById(1L)).thenThrow(new ResourceNotFoundException("Policy not found"));

        ResponseEntity<Policy> response = policyController.getPolicyById(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(policyService, times(1)).getPolicyById(1L);
    }
}
