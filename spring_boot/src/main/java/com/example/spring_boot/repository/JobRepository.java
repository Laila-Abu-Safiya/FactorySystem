package com.example.spring_boot.repository;

import com.example.spring_boot.Entity.Job;
import org.aspectj.apache.bcel.classfile.Module;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
    @Query("SELECT j FROM Job j where j.machineid = ?1")
    Optional<Job> findJobByMachineId(int machineid);
}