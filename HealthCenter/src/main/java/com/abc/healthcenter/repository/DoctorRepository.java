package com.abc.healthcenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.healthcenter.entity.DoctorEntity;

public interface DoctorRepository extends JpaRepository<DoctorEntity, Integer> {

}
