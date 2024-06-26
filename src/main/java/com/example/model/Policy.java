package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Policy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long policyId;
	private String policyName;
	private String policyDescription;
	private String termsAndConditons;
	public Long getPolicyId() {
		return policyId;
	}
	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}
	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	public String getPolicyDescription() {
		return policyDescription;
	}
	public void setPolicyDescription(String policyDescription) {
		this.policyDescription = policyDescription;
	}
	public String getTermsAndConditons() {
		return termsAndConditons;
	}
	public void setTermsAndConditons(String termsAndConditons) {
		this.termsAndConditons = termsAndConditons;
	}
	@Override
	public String toString() {
		return "Policy [policyId=" + policyId + ", policyName=" + policyName + ", policyDescription="
				+ policyDescription + ", termsAndConditons=" + termsAndConditons + "]";
	}
	public Policy(Long policyId, String policyName, String policyDescription, String termsAndConditons) {
		super();
		this.policyId = policyId;
		this.policyName = policyName;
		this.policyDescription = policyDescription;
		this.termsAndConditons = termsAndConditons;
	}
	public Policy() {
		super();
		
	}
	

}
