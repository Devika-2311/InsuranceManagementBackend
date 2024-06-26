package com.example.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PolicyTest {

    @Test
    public void testPolicyConstructorAndGetters() {
        Policy policy = new Policy(1L, "Health Insurance", "Provides coverage for medical expenses", "Terms and conditions apply");

        assertEquals(1L, policy.getPolicyId());
        assertEquals("Health Insurance", policy.getPolicyName());
        assertEquals("Provides coverage for medical expenses", policy.getPolicyDescription());
        assertEquals("Terms and conditions apply", policy.getTermsAndConditons());
    }

    @Test
    public void testPolicySetters() {
        Policy policy = new Policy();

        policy.setPolicyId(1L);
        policy.setPolicyName("Health Insurance");
        policy.setPolicyDescription("Provides coverage for medical expenses");
        policy.setTermsAndConditons("Terms and conditions apply");

        assertEquals(1L, policy.getPolicyId());
        assertEquals("Health Insurance", policy.getPolicyName());
        assertEquals("Provides coverage for medical expenses", policy.getPolicyDescription());
        assertEquals("Terms and conditions apply", policy.getTermsAndConditons());
    }

    @Test
    public void testToString() {
        Policy policy = new Policy(1L, "Health Insurance", "Provides coverage for medical expenses", "Terms and conditions apply");

        String expectedString = "Policy [policyId=1, policyName=Health Insurance, policyDescription=Provides coverage for medical expenses, termsAndConditons=Terms and conditions apply]";
        assertEquals(expectedString, policy.toString());
    }

    @Test
    public void testDefaultConstructor() {
        Policy policy = new Policy();
        assertNotNull(policy);
    }
}
