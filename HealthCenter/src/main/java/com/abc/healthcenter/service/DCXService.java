package com.abc.healthcenter.service;

import com.abc.healthcenter.exceptions.ResourceAlreadyExistException;
import com.abc.healthcenter.exceptions.ResourceNotFoundException;

import com.abc.healthcenter.model.Doctor;

public interface DCXService {

	 public void saveDoctor(Doctor doctor) throws ResourceAlreadyExistException;
	 public Doctor findDoctorbyId(int id) throws ResourceNotFoundException;
	 public void deleteDoctorbyId(int id)throws ResourceNotFoundException;
	 
	 
}
