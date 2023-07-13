 package xray.facility.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import xray.facility.entity.FacilitySite;
																				//JpaRepository interface provides methods for performing CRUD operations on entities 
public interface FacilitySiteDao extends JpaRepository<FacilitySite, Long>{ 	

}

//JpaRepository interface provides methods for performing CRUD operations on entities
//Allows FacilitySiteDao interface to inherit CRUD operations to interact with relational database
//FacilitySiteDao interface acts as DAO designed for FacilitySite entities 
