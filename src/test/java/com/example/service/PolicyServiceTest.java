package com.example.service;

import com.example.exp.ResourceNotFoundException;
import com.example.model.Policy;
import com.example.repo.PolicyRepo;
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
public class PolicyServiceTest {

    @Mock
    private PolicyRepo policyRepository;

    @InjectMocks
    private PolicyService policyService;

    @Test
    public void testGetPolicyById_PolicyFound() throws ResourceNotFoundException {
        Policy policy = new Policy();
        policy.setPolicyId(1L);

        when(policyRepository.findById(1L)).thenReturn(Optional.of(policy));

        Policy foundPolicy = policyService.getPolicyById(1L);
        assertEquals(policy, foundPolicy);
        verify(policyRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetPolicyById_PolicyNotFound() {
        when(policyRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            policyService.getPolicyById(1L);
        });

        verify(policyRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetAllPolicies() {
        Policy policy1 = new Policy();
        policy1.setPolicyId(1L);
        Policy policy2 = new Policy();
        policy2.setPolicyId(2L);

        when(policyRepository.findAll()).thenReturn(Arrays.asList(policy1, policy2));

        List<Policy> policies = policyService.getAllPolicies();
        assertEquals(2, policies.size());
        verify(policyRepository, times(1)).findAll();
    }
}
