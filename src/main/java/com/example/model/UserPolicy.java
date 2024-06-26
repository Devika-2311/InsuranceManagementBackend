package com.example.model;
 
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
 
@Entity
public class UserPolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userPolicyId;
    private float coverage;
    private int term;
    private float premium;
    private String premiumTerm;
    private int premiumCount;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private float leftcoverage;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "policyId")
    private Policy policy;
	public Long getUserPolicyId() {
		return userPolicyId;
	}
	public void setUserPolicyID(Long userPolicyId2) {
		this.userPolicyId = userPolicyId2;
	}
	public float getCoverage() {
		return coverage;
	}
	public float getLeftcoverage() {
		return leftcoverage;
	}
	public void setLeftcoverage(float leftcoverage) {
		this.leftcoverage = leftcoverage;
	}
	public void setCoverage(Float string) {
		this.coverage = string;
	}
	public int getTerm() {
		return term;
	}
	public void setTerm(int term) {
		this.term = term;
	}
	public float getPremium() {
		return premium;
	}
	public void setPremium(float premium) {
		this.premium = premium;
	}
	public String getPremiumTerm() {
		return premiumTerm;
	}
	public void setPremiumTerm(String premiumTerm) {
		this.premiumTerm = premiumTerm;
	}
	public int getPremiumCount() {
		return premiumCount;
	}
	public void setPremiumCount(int premiumCount) {
		this.premiumCount = premiumCount;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Policy getPolicy() {
		return policy;
	}
	public void setPolicy(Policy policy) {
		this.policy = policy;
	}
	@Override
	public String toString() {
		return "UserPolicy [userPolicyId=" + userPolicyId + ", coverage=" + coverage + ", term=" + term + ", premium="
				+ premium + ", premiumTerm=" + premiumTerm + ", premiumCount=" + premiumCount + ", startDate="
				+ startDate + ", endDate=" + endDate + ", status=" + status + ", leftcoverage=" + leftcoverage
				+ ", user=" + user + ", policy=" + policy + "]";
	}
	public UserPolicy(Long userPolicyId, float coverage, int term, float premium, String premiumTerm, int premiumCount,
			LocalDate startDate, LocalDate endDate, String status, float leftcoverage, User user, Policy policy) {
		super();
		this.userPolicyId = userPolicyId;
		this.coverage = coverage;
		this.term = term;
		this.premium = premium;
		this.premiumTerm = premiumTerm;
		this.premiumCount = premiumCount;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.leftcoverage = leftcoverage;
		this.user = user;
		this.policy = policy;
	}
	public UserPolicy() {
		super();
	}
	
	
	
 
}
 