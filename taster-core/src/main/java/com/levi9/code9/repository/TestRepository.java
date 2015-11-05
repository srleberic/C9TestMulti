package com.levi9.code9.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.levi9.code9.model.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
}
