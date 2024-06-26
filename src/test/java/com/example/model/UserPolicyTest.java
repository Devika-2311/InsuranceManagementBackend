package com.example.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;



class UserPolicyTest {

    @Test
    public void testUserPolicyConstructorAndGetters() {
        LocalDate startDate = LocalDate.of(2024, 6, 22);
        LocalDate endDate = LocalDate.of(2025, 6, 22);
        User user = new User(1L, "John", "Doe", LocalDate.of(1990, 5, 15), "1234567890", "john.doe@example.com", "Address", "password", "user", LocalDate.now());
        Policy policy = new Policy(1L, "Health Insurance", "Covers medical expenses", "Terms and conditions apply");

        UserPolicy userPolicy = new UserPolicy(1L, 5000.0f, 12, 12000.0f, "Annual", 1, startDate, endDate, "Active", 5000.0f, user, policy);

        assertEquals(1L, userPolicy.getUserPolicyId());
        assertEquals(5000.0f, userPolicy.getCoverage());
        assertEquals(12, userPolicy.getTerm());
        assertEquals(12000.0f, userPolicy.getPremium());
        assertEquals("Annual", userPolicy.getPremiumTerm());
        assertEquals(1, userPolicy.getPremiumCount());
        assertEquals(startDate, userPolicy.getStartDate());
        assertEquals(endDate, userPolicy.getEndDate());
        assertEquals("Active", userPolicy.getStatus());
        assertEquals(5000.0f, userPolicy.getLeftcoverage());
        assertEquals(user, userPolicy.getUser());
        assertEquals(policy, userPolicy.getPolicy());
    }

    @Test
    public void testUserPolicySetters() {
        UserPolicy userPolicy = new UserPolicy();

        userPolicy.setUserPolicyID(1L);
        userPolicy.setCoverage(5000.0f);
        userPolicy.setTerm(12);
        userPolicy.setPremium(12000.0f);
        userPolicy.setPremiumTerm("Annual");
        userPolicy.setPremiumCount(1);
        LocalDate startDate = LocalDate.of(2024, 6, 22);
        userPolicy.setStartDate(startDate);
        LocalDate endDate = LocalDate.of(2025, 6, 22);
        userPolicy.setEndDate(endDate);
        userPolicy.setStatus("Active");
        userPolicy.setLeftcoverage(5000.0f);
        User user = new User(1L, "John", "Doe", LocalDate.of(1990, 5, 15), "1234567890", "john.doe@example.com", "Address", "password", "user", LocalDate.now());
        userPolicy.setUser(user);
        Policy policy = new Policy(1L, "Health Insurance", "Covers medical expenses", "Terms and conditions apply");
        userPolicy.setPolicy(policy);

        assertEquals(1L, userPolicy.getUserPolicyId());
        assertEquals(5000.0f, userPolicy.getCoverage());
        assertEquals(12, userPolicy.getTerm());
        assertEquals(12000.0f, userPolicy.getPremium());
        assertEquals("Annual", userPolicy.getPremiumTerm());
        assertEquals(1, userPolicy.getPremiumCount());
        assertEquals(startDate, userPolicy.getStartDate());
        assertEquals(endDate, userPolicy.getEndDate());
        assertEquals("Active", userPolicy.getStatus());
        assertEquals(5000.0f, userPolicy.getLeftcoverage());
        assertEquals(user, userPolicy.getUser());
        assertEquals(policy, userPolicy.getPolicy());
    }

    @Test
    public void testToString() {
        LocalDate startDate = LocalDate.of(2024, 6, 22);
        LocalDate endDate = LocalDate.of(2025, 6, 22);
        User user = new User(1L, "John", "Doe", LocalDate.of(1990, 5, 15), "1234567890", "john.doe@example.com", "Address", "password", "user", LocalDate.now());
        Policy policy = new Policy(1L, "Health Insurance", "Covers medical expenses", "Terms and conditions apply");

        UserPolicy userPolicy = new UserPolicy(1L, 5000.0f, 12, 12000.0f, "Annual", 1, startDate, endDate, "Active", 5000.0f, user, policy);

        String expectedString = "UserPolicy [userPolicyId=1, coverage=5000.0, term=12, premium=12000.0, premiumTerm=Annual, premiumCount=1, startDate=2024-06-22, endDate=2025-06-22, status=Active, leftcoverage=5000.0, user=User [userId=1, firstName=John, lastName=Doe, dob=1990-05-15, phoneNo=1234567890, emailId=john.doe@example.com, address=Address, password=password, role=user, createdAt=" + LocalDate.now() + "], policy=Policy [policyId=1, policyName=Health Insurance, policyDescription=Covers medical expenses, termsAndConditons=Terms and conditions apply]]";

        assertEquals(expectedString, userPolicy.toString());
    }

    @Test
    public void testDefaultConstructor() {
        UserPolicy userPolicy = new UserPolicy();
        assertNotNull(userPolicy);
    }
}

