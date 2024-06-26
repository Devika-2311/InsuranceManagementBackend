package com.example.model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class PolicyDocumentTest {

	@Test
   void testPolicyDocumentConstructorAndGetters() {
        LocalDate purchaseDate = LocalDate.of(2024, 6, 22);
        UserPolicy userPolicy = new UserPolicy(1L, 5000.0f, 12, 12000.0f, "Annual", 1, LocalDate.now(), LocalDate.now().plusYears(1), "Active", 5000.0f, new User(), new Policy());
        PolicyType policyType = PolicyType.HEALTH;
        PolicyDocument policyDocument = new PolicyDocument(1L, userPolicy, policyType, 50000.0, true, "Jane Doe", "Spouse", "jane.doe@example.com", "ID123", 30, 170.0, 65.0, false, false, false, false, "None", "Healthy", "ModelX", "ABC123", 30000.0, "Personal", "Sedan", 25, "CheatSheet123", purchaseDate);

        assertAll("PolicyDocument getters",
            () -> assertEquals(1L, policyDocument.getPolicyDetailsId()),
            () -> assertEquals(userPolicy, policyDocument.getUserPolicy()),
            () -> assertEquals(policyType, policyDocument.getPolicyType()),
            () -> assertEquals(50000.0, policyDocument.getAnnualIncome()),
            () -> assertEquals(true, policyDocument.getAnyDisease()),
            () -> assertEquals("Jane Doe", policyDocument.getNomineeName()),
            () -> assertEquals("Spouse", policyDocument.getNomineeRelation()),
            () -> assertEquals("jane.doe@example.com", policyDocument.getNomineeEmail()),
            () -> assertEquals("ID123", policyDocument.getNomineeProof()),
            () -> assertEquals(30, policyDocument.getAge()),
            () -> assertEquals(170.0, policyDocument.getHeight()),
            () -> assertEquals(65.0, policyDocument.getWeight()),
            () -> assertEquals(false, policyDocument.getSmoke()),
            () -> assertEquals(false, policyDocument.getAlcohol()),
            () -> assertEquals(false, policyDocument.getBp()),
            () -> assertEquals(false, policyDocument.getDiabetics()),
            () -> assertEquals("None", policyDocument.getCriticalDisease()),
            () -> assertEquals("Healthy", policyDocument.getHealthReport()),
            () -> assertEquals("ModelX", policyDocument.getVehicleModelNo()),
            () -> assertEquals("ABC123", policyDocument.getLicensePlateNo()),
            () -> assertEquals(30000.0, policyDocument.getVehicleValue()),
            () -> assertEquals("Personal", policyDocument.getPrimaryUse()),
            () -> assertEquals("Sedan", policyDocument.getVehicleType()),
            () -> assertEquals(25, policyDocument.getDriverAge()),
            () -> assertEquals("CheatSheet123", policyDocument.getCheatSheet()),
            () -> assertEquals(purchaseDate, policyDocument.getPurchaseDate())
        );
    }

    @Test
    void testPolicyDocumentSetters() {
        PolicyDocument policyDocument = new PolicyDocument();

        policyDocument.setPolicyDetailsId(1L);
        UserPolicy userPolicy = new UserPolicy(1L, 5000.0f, 12, 12000.0f, "Annual", 1, LocalDate.now(), LocalDate.now().plusYears(1), "Active", 5000.0f, new User(), new Policy());
        policyDocument.setUserPolicy(userPolicy);
        PolicyType policyType = PolicyType.AUTO;
        policyDocument.setPolicyType(policyType);
        policyDocument.setAnnualIncome(60000.0);
        policyDocument.setAnyDisease(false);
        policyDocument.setNomineeName("John Doe");
        policyDocument.setNomineeRelation("Brother");
        policyDocument.setNomineeEmail("john.doe@example.com");
        policyDocument.setNomineeProof("ID456");
        policyDocument.setAge(35);
        policyDocument.setHeight(180.0);
        policyDocument.setWeight(75.0);
        policyDocument.setSmoke(true);
        policyDocument.setAlcohol(true);
        policyDocument.setBp(true);
        policyDocument.setDiabetics(true);
        policyDocument.setCriticalDisease("Diabetes");
        policyDocument.setHealthReport("Report");
        policyDocument.setVehicleModelNo("ModelY");
        policyDocument.setLicensePlateNo("DEF456");
        policyDocument.setVehicleValue(35000.0);
        policyDocument.setPrimaryUse("Commercial");
        policyDocument.setVehicleType("Truck");
        policyDocument.setDriverAge(30);
        policyDocument.setCheatSheet("CheatSheet456");
        LocalDate purchaseDate = LocalDate.of(2023, 5, 20);
        policyDocument.setPurchaseDate(purchaseDate);

        assertEquals(1L, policyDocument.getPolicyDetailsId());
        assertEquals(userPolicy, policyDocument.getUserPolicy());
        assertEquals(policyType, policyDocument.getPolicyType());
        assertEquals(60000.0, policyDocument.getAnnualIncome());
        assertEquals(false, policyDocument.getAnyDisease());
        assertEquals("John Doe", policyDocument.getNomineeName());
        assertEquals("Brother", policyDocument.getNomineeRelation());
        assertEquals("john.doe@example.com", policyDocument.getNomineeEmail());
        assertEquals("ID456", policyDocument.getNomineeProof());
        assertEquals(35, policyDocument.getAge());
        assertEquals(180.0, policyDocument.getHeight());
        assertEquals(75.0, policyDocument.getWeight());
        assertEquals(true, policyDocument.getSmoke());
        assertEquals(true, policyDocument.getAlcohol());
        assertEquals(true, policyDocument.getBp());
        assertEquals(true, policyDocument.getDiabetics());
        assertEquals("Diabetes", policyDocument.getCriticalDisease());
        assertEquals("Report", policyDocument.getHealthReport());
        assertEquals("ModelY", policyDocument.getVehicleModelNo());
        assertEquals("DEF456", policyDocument.getLicensePlateNo());
        assertEquals(35000.0, policyDocument.getVehicleValue());
        assertEquals("Commercial", policyDocument.getPrimaryUse());
        assertEquals("Truck", policyDocument.getVehicleType());
        assertEquals(30, policyDocument.getDriverAge());
        assertEquals("CheatSheet456", policyDocument.getCheatSheet());
        assertEquals(purchaseDate, policyDocument.getPurchaseDate());
    }

    @Test
    public void testToString() {
        LocalDate purchaseDate = LocalDate.of(2024, 6, 22);
        UserPolicy userPolicy = new UserPolicy(1L, 5000.0f, 12, 12000.0f, "Annual", 1, LocalDate.now(), LocalDate.now().plusYears(1), "Active", 5000.0f, new User(), new Policy());
        PolicyType policyType = PolicyType.HEALTH;

        PolicyDocument policyDocument = new PolicyDocument(1L, userPolicy, policyType, 50000.0, true, "Jane Doe", "Spouse", "jane.doe@example.com", "ID123", 30, 170.0, 65.0, false, false, false, false, "None", "Healthy", "ModelX", "ABC123", 30000.0, "Personal", "Sedan", 25, "CheatSheet123", purchaseDate);

        String expectedString = "PolicyDocument [policyDetailsId=1, userPolicy=" + userPolicy.toString() + ", policyType=HEALTH, annualIncome=50000.0, anyDisease=true, nomineeName=Jane Doe, nomineeRelation=Spouse, nomineeEmail=jane.doe@example.com, nomineeProof=ID123, age=30, height=170.0, weight=65.0, smoke=false, alcohol=false, bp=false, diabetics=false, criticalDisease=None, healthReport=Healthy, vehicleModelNo=ModelX, licensePlateNo=ABC123, vehicleValue=30000.0, primaryUse=Personal, vehicleType=Sedan, driverAge=25, cheatSheet=CheatSheet123, purchaseDate=2024-06-22]";

        assertEquals(expectedString, policyDocument.toString());
    }

    @Test
    public void testDefaultConstructor() {
        PolicyDocument policyDocument = new PolicyDocument();
        assertNotNull(policyDocument);
    }
}
