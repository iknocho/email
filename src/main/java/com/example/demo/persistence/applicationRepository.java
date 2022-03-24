package com.example.demo.persistence;

import com.example.demo.model.applicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface applicationRepository extends JpaRepository<applicationEntity,String> {
    List<applicationEntity> findByCompanyName(String companyName);
}
