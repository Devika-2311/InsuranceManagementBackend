package com.example.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class PolicyDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long policyDetailsId;

    @OneToOne
    @JoinColumn(name = "user_policy_id")
    private UserPolicy userPolicy;

    @Enumerated(EnumType.STRING)
    private PolicyType policyType;

    
    private Double annualIncome;
    private Boolean anyDisease;
    private String nomineeName;
    private String nomineeRelation;
    private String nomineeEmail;
    private String nomineeProof;

    
    private Integer age;
    private Double height;
    private Double weight;
    private Boolean smoke;
    private Boolean alcohol;
    private Boolean bp;
    private Boolean diabetics;
    private String criticalDisease;
    private String healthReport;

    
    private String vehicleModelNo;
    private String licensePlateNo;
    private Double vehicleValue;
    private String primaryUse;
    private String vehicleType;
    private Integer driverAge;
    private String cheatSheet;
    private LocalDate purchaseDate;

    // Getters and Setters

    public Long getPolicyDetailsId() {
        return policyDetailsId;
    }

    public void setPolicyDetailsId(Long policyDetailsId) {
        this.policyDetailsId = policyDetailsId;
    }

    public UserPolicy getUserPolicy() {
        return userPolicy;
    }

    public void setUserPolicy(UserPolicy userPolicy2) {
        this.userPolicy = userPolicy2;
    }

    public PolicyType getPolicyType() {
        return policyType;
    }

    public void setPolicyType(PolicyType policyType) {
        this.policyType = policyType;
    }

    // Term policy specific getters and setters
    public Double getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(Double annualIncome2) {
        this.annualIncome = annualIncome2;
    }

    public Boolean getAnyDisease() {
        return anyDisease;
    }

    public void setAnyDisease(Boolean anyDisease2) {
        this.anyDisease = anyDisease2;
    }

    public String getNomineeName() {
        return nomineeName;
    }

    public void setNomineeName(String nomineeName) {
        this.nomineeName = nomineeName;
    }

    public String getNomineeRelation() {
        return nomineeRelation;
    }

    public void setNomineeRelation(String nomineeRelation) {
        this.nomineeRelation = nomineeRelation;
    }

    public String getNomineeEmail() {
        return nomineeEmail;
    }

    public void setNomineeEmail(String nomineeEmail) {
        this.nomineeEmail = nomineeEmail;
    }

    public String getNomineeProof() {
        return nomineeProof;
    }

    public void setNomineeProof(String nomineeProof) {
        this.nomineeProof = nomineeProof;
    }

    // Health policy specific getters and setters
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Boolean getSmoke() {
        return smoke;
    }

    public void setSmoke(Boolean smoke) {
        this.smoke = smoke;
    }

    public Boolean getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(Boolean alcohol) {
        this.alcohol = alcohol;
    }

    public Boolean getBp() {
        return bp;
    }

    public void setBp(Boolean bp) {
        this.bp = bp;
    }

    public Boolean getDiabetics() {
        return diabetics;
    }

    public void setDiabetics(Boolean diabetics) {
        this.diabetics = diabetics;
    }

    public String getCriticalDisease() {
        return criticalDisease;
    }

    public void setCriticalDisease(String criticalDisease) {
        this.criticalDisease = criticalDisease;
    }

    public String getHealthReport() {
        return healthReport;
    }

    public void setHealthReport(String healthReport) {
        this.healthReport = healthReport;
    }

    // Auto policy specific getters and setters
    public String getVehicleModelNo() {
        return vehicleModelNo;
    }

    public void setVehicleModelNo(String vehicleModelNo) {
        this.vehicleModelNo = vehicleModelNo;
    }

    public String getLicensePlateNo() {
        return licensePlateNo;
    }

    public void setLicensePlateNo(String licensePlateNo) {
        this.licensePlateNo = licensePlateNo;
    }

    public Double getVehicleValue() {
        return vehicleValue;
    }

    public void setVehicleValue(Double vehicleValue) {
        this.vehicleValue = vehicleValue;
    }

    public String getPrimaryUse() {
        return primaryUse;
    }

    public void setPrimaryUse(String primaryUse) {
        this.primaryUse = primaryUse;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Integer getDriverAge() {
        return driverAge;
    }

    public void setDriverAge(Integer driverAge) {
        this.driverAge = driverAge;
    }

    public String getCheatSheet() {
        return cheatSheet;
    }

    public void setCheatSheet(String cheatSheet) {
        this.cheatSheet = cheatSheet;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

	public PolicyDocument() {
		super();
		
	}

	public PolicyDocument(Long policyDetailsId, UserPolicy userPolicy, PolicyType policyType, Double annualIncome,
			Boolean anyDisease, String nomineeName, String nomineeRelation, String nomineeEmail, String nomineeProof,
			Integer age, Double height, Double weight, Boolean smoke, Boolean alcohol, Boolean bp, Boolean diabetics,
			String criticalDisease, String healthReport, String vehicleModelNo, String licensePlateNo,
			Double vehicleValue, String primaryUse, String vehicleType, Integer driverAge, String cheatSheet,
			LocalDate purchaseDate) {
		super();
		this.policyDetailsId = policyDetailsId;
		this.userPolicy = userPolicy;
		this.policyType = policyType;
		this.annualIncome = annualIncome;
		this.anyDisease = anyDisease;
		this.nomineeName = nomineeName;
		this.nomineeRelation = nomineeRelation;
		this.nomineeEmail = nomineeEmail;
		this.nomineeProof = nomineeProof;
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.smoke = smoke;
		this.alcohol = alcohol;
		this.bp = bp;
		this.diabetics = diabetics;
		this.criticalDisease = criticalDisease;
		this.healthReport = healthReport;
		this.vehicleModelNo = vehicleModelNo;
		this.licensePlateNo = licensePlateNo;
		this.vehicleValue = vehicleValue;
		this.primaryUse = primaryUse;
		this.vehicleType = vehicleType;
		this.driverAge = driverAge;
		this.cheatSheet = cheatSheet;
		this.purchaseDate = purchaseDate;
	}

	@Override
	public String toString() {
		return "PolicyDocument [policyDetailsId=" + policyDetailsId + ", userPolicy=" + userPolicy + ", policyType="
				+ policyType + ", annualIncome=" + annualIncome + ", anyDisease=" + anyDisease + ", nomineeName="
				+ nomineeName + ", nomineeRelation=" + nomineeRelation + ", nomineeEmail=" + nomineeEmail
				+ ", nomineeProof=" + nomineeProof + ", age=" + age + ", height=" + height + ", weight=" + weight
				+ ", smoke=" + smoke + ", alcohol=" + alcohol + ", bp=" + bp + ", diabetics=" + diabetics
				+ ", criticalDisease=" + criticalDisease + ", healthReport=" + healthReport + ", vehicleModelNo="
				+ vehicleModelNo + ", licensePlateNo=" + licensePlateNo + ", vehicleValue=" + vehicleValue
				+ ", primaryUse=" + primaryUse + ", vehicleType=" + vehicleType + ", driverAge=" + driverAge
				+ ", cheatSheet=" + cheatSheet + ", purchaseDate=" + purchaseDate + "]";
	}
}
