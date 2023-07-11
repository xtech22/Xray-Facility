package xray.facility.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import xray.facility.entity.Patient;

public interface PatientDao extends JpaRepository<Patient, Long> {

}


