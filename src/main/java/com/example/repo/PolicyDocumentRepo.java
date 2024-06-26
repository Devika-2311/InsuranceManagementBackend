package com.example.repo;

import com.example.model.PolicyDocument;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface PolicyDocumentRepo extends JpaRepository<PolicyDocument, Long> {

    
}
