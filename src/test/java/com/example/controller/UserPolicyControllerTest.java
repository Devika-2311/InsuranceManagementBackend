package com.example.controller;

import com.example.model.UserPolicy;
import com.example.service.UserPolicyService;
import com.example.exp.ResourceNotFoundException;
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
public class UserPolicyControllerTest {

    @Mock
    private UserPolicyService userPolicyService;

    @InjectMocks
    private UserPolicyController userPolicyController;

    @Test
    public void testCreateUserPolicy() {
    	UserPolicy userPolicy = new UserPolicy();
        userPolicy.setUserPolicyID(1L);
        when(userPolicyService.createUserPolicy(any(UserPolicy.class))).thenReturn(userPolicy);

        ResponseEntity<Object> response = userPolicyController.createUserPolicy(userPolicy);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(userPolicy.getUserPolicyId(), response.getBody());
        verify(userPolicyService, times(1)).createUserPolicy(userPolicy);
    }
   


    @Test
    public void testGetPoliciesById_UserFound() throws ResourceNotFoundException {
        UserPolicy policy1 = new UserPolicy();
        policy1.setUserPolicyID(1L);
        UserPolicy policy2 = new UserPolicy();
        policy2.setUserPolicyID(2L);

        when(userPolicyService.getUserPoliciesByUserId(1L)).thenReturn(Arrays.asList(policy1, policy2));

        ResponseEntity<List<UserPolicy>> response = userPolicyController.getPoliciesById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        verify(userPolicyService, times(1)).getUserPoliciesByUserId(1L);
    }

    @Test
    public void testGetPoliciesById_UserNotFound() throws ResourceNotFoundException {
        when(userPolicyService.getUserPoliciesByUserId(1L)).thenThrow(new ResourceNotFoundException("No policies found for user id: " + 1L));

        ResponseEntity<List<UserPolicy>> response = userPolicyController.getPoliciesById(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(userPolicyService, times(1)).getUserPoliciesByUserId(1L);
    }

    @Test
    public void testGetAllUserPolicies_PoliciesFound() {
        UserPolicy policy1 = new UserPolicy();
        policy1.setUserPolicyID(1L);
        UserPolicy policy2 = new UserPolicy();
        policy2.setUserPolicyID(2L);

        when(userPolicyService.getAllUserPolicies()).thenReturn(Arrays.asList(policy1, policy2));

        ResponseEntity<List<UserPolicy>> response = userPolicyController.getAllUserPolicies();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        verify(userPolicyService, times(1)).getAllUserPolicies();
    }

    @Test
    public void testGetAllUserPolicies_NoPoliciesFound() {
        when(userPolicyService.getAllUserPolicies()).thenReturn(Arrays.asList());

        ResponseEntity<List<UserPolicy>> response = userPolicyController.getAllUserPolicies();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userPolicyService, times(1)).getAllUserPolicies();
    }

    @Test
    public void testGetUserPoliciesByUserId_PoliciesFound() throws ResourceNotFoundException {
        UserPolicy policy1 = new UserPolicy();
        policy1.setUserPolicyID(1L);
        UserPolicy policy2 = new UserPolicy();
        policy2.setUserPolicyID(2L);

        when(userPolicyService.getUserPoliciesByUserId(1L)).thenReturn(Arrays.asList(policy1, policy2));

        ResponseEntity<List<UserPolicy>> response = userPolicyController.getUserPoliciesByUserId(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        verify(userPolicyService, times(1)).getUserPoliciesByUserId(1L);
    }

    @Test
    public void testGetUserPoliciesByUserId_NoPoliciesFound() throws ResourceNotFoundException {
        when(userPolicyService.getUserPoliciesByUserId(1L)).thenReturn(Arrays.asList());

        ResponseEntity<List<UserPolicy>> response = userPolicyController.getUserPoliciesByUserId(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userPolicyService, times(1)).getUserPoliciesByUserId(1L);
    }
}
