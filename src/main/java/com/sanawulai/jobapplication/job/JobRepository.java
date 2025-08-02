package com.sanawulai.jobapplication.job;

import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.ScopedValue;

public interface JobRepository extends JpaRepository<Job, Long >{
    ScopedValue<Object> findAllById(long id);
}
