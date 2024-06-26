package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Policy;

public interface PolicyRepo extends JpaRepository<Policy,Long> {

}