package com.example.service;

import com.example.model.UserPolicy;
import com.example.repo.UserPolicyRepo;
import com.example.exp.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserPolicyServiceTest {

    @Mock
    private UserPolicyRepo userPolicyRepository;

    @InjectMocks
    private UserPolicyService userPolicyService;

    @Test
    public void testCreateUserPolicy() {
        UserPolicy userPolicy = new UserPolicy();
        userPolicy.setUserPolicyID(1L);

        when(userPolicyRepository.save(userPolicy)).thenReturn(userPolicy);

        UserPolicy createdUserPolicy = userPolicyService.createUserPolicy(userPolicy);
        assertEquals(userPolicy, createdUserPolicy);
        verify(userPolicyRepository, times(1)).save(userPolicy);
    }

    @Test
    public void testGetAllUserPolicies() {
        UserPolicy policy1 = new UserPolicy();
        policy1.setUserPolicyID(1L);
        UserPolicy policy2 = new UserPolicy();
        policy2.setUserPolicyID(2L);

        when(userPolicyRepository.findAll()).thenReturn(Arrays.asList(policy1, policy2));

        List<UserPolicy> policies = userPolicyService.getAllUserPolicies();
        assertEquals(2, policies.size());
        verify(userPolicyRepository, times(1)).findAll();
    }

    @Test
    public void testGetUserPoliciesByUserId_UserFound() throws ResourceNotFoundException {
        UserPolicy policy = new UserPolicy();
        policy.setUserPolicyID(1L);

        when(userPolicyRepository.findAllByUser_UserId(1L)).thenReturn(Arrays.asList(policy));

        List<UserPolicy> policies = userPolicyService.getUserPoliciesByUserId(1L);
        assertFalse(policies.isEmpty());
        assertEquals(1, policies.size());
        verify(userPolicyRepository, times(1)).findAllByUser_UserId(1L);
    }

    @Test
    public void testGetUserPoliciesByUserId_UserNotFound() {
        when(userPolicyRepository.findAllByUser_UserId(1L)).thenReturn(Arrays.asList());

        assertThrows(ResourceNotFoundException.class, () -> {
            userPolicyService.getUserPoliciesByUserId(1L);
        });

        verify(userPolicyRepository, times(1)).findAllByUser_UserId(1L);
    }

    @Test
    public void testGetUserPolicyByUserPolicyId_PolicyFound() throws ResourceNotFoundException {
        UserPolicy policy = new UserPolicy();
        policy.setUserPolicyID(1L);

        when(userPolicyRepository.findById(1L)).thenReturn(Optional.of(policy));

        UserPolicy foundPolicy = userPolicyService.getUserPolicyByUserPolicyId(1L);
        assertEquals(policy, foundPolicy);
        verify(userPolicyRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetUserPolicyByUserPolicyId_PolicyNotFound() {
        when(userPolicyRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            userPolicyService.getUserPolicyByUserPolicyId(1L);
        });

        verify(userPolicyRepository, times(1)).findById(1L);
    }
}
