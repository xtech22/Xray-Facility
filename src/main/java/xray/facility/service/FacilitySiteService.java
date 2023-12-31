package xray.facility.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xray.facility.dao.FacilitySiteDao;
import xray.facility.dao.PatientDao;
import xray.facility.dao.TechDao;
import xray.facility.entity.FacilitySite;
import xray.facility.entity.Patient;
import xray.facility.entity.Tech;
import xray.facility.model.XrayFacilityData;
import xray.facility.model.XrayFacilityData.FacilitySitePatient;
import xray.facility.model.XrayFacilityData.FacilitySiteTech;
@Service							//Business Logistics that takes Interface from DAO layer
public class FacilitySiteService {
									//Spring can inject DAO object into the variable autowired
    								//FacilitySiteDao object named facilitySiteDao as pvt instance variable
	
	
	
	
	
  	@Autowired
  	private FacilitySiteDao facilitySiteDao;
  	@Autowired 
  	private TechDao techDao;
  	@Autowired
  	private PatientDao patientDao;
  	
  	
  	
  //Method below saves any new facility site. 
  //FacilitySiteData object as a parameter and return a FacilitySiteData object
  	
    public XrayFacilityData saveFacilitySite(XrayFacilityData XrayFacilityData) {
  Long facilitySiteId = XrayFacilityData.getFacilitySiteId();
  FacilitySite facilitySite  = findOrCreateFacilitySite(facilitySiteId);
  
  copyFacilitySiteFields(facilitySite, XrayFacilityData);
  return new XrayFacilityData(facilitySiteDao.save(facilitySite));//Call the FacilitySiteDao method save(facilitySite). Return a new FacilitySiteData object created from the return value of the save() method.
  }
    
    
  //Call copyFacilitySiteFields(). This method takes a FacilitySite object and a FacilitySiteData object as parameters. Matching fields are copied from the FacilitySiteData object to  FacilitySite object.
    private void copyFacilitySiteFields(FacilitySite facilitySite, XrayFacilityData xrayFacilityData) {
    	facilitySite.setFacilitySiteId(xrayFacilityData.getFacilitySiteId());
    	facilitySite.setFacilitySiteName(xrayFacilityData.getFacilitySiteName());
    	facilitySite.setFacilitySiteModality(xrayFacilityData.getFacilitySiteModality());
 }
    
    
    // method finds facility site by Id using facilitySiteDao. If a facility site with the given ID is found, it is returned. If no facility site is found, it throws a NoSuchElementException with an error message.
 	private FacilitySite findFacilitySiteById(Long facilitySiteId)  {
    return facilitySiteDao.findById(facilitySiteId)
   .orElseThrow(() -> new NoSuchElementException("Facility site with ID=" + facilitySiteId + " was not found."));
    }
 	
 	
 	 //This method either finds an existing facility site by its ID using the findFacilitySiteById method, or creates a new FacilitySite object if the clinicSiteId is null. If clinicSiteId is null, it creates a new ClinicSite instance. If  clinicSiteId is not null, it calls findClinicSiteById method to get corresponding ClinicSite instance. Method returns the obtained ClinicSite object.
    private FacilitySite findOrCreateFacilitySite(Long facilitySiteId) {
	FacilitySite facilitySite;
	if(Objects.isNull(facilitySiteId)) {
	facilitySite = new FacilitySite();
	}
	else {
	facilitySite = findFacilitySiteById(facilitySiteId);
	}
	return facilitySite;
    }
   
    ////////////////saveTech method/////////////////
    @Transactional(readOnly = false)
    public FacilitySiteTech saveTech(Long facilitySiteId, FacilitySiteTech facilitySiteTech) { //method signature
    FacilitySite facilitySite = findFacilitySiteById(facilitySiteId);
	Long techId = facilitySiteTech.getTechId();
	Tech tech = findOrCreateTech(facilitySiteId, techId);
	
	copyTechFields(tech, facilitySiteTech);
	  
	  																		//set facilitySite in tech
																			//add tech to facility site list of techs
	tech.setFacilitySite(facilitySite);
	facilitySite.getTechs().add(tech);
	
	Tech dbTech = techDao.save(tech);
	return new FacilitySiteTech(dbTech);
    }
    
    
    public FacilitySiteTech updateTech (Long techId, Tech tech ) { 
    	FacilitySiteTech facilitySiteTech = new FacilitySiteTech(tech);
		facilitySiteTech.setTechId(techId);
		return facilitySiteTech;
    }

    																// Note that findById returns an Optional. If the Optional is
    																		// empty .orElseThrow throws a NoSuchElementException. If the
    																		// Optional is not empty an Employee is returned.
         
    
        private Tech findTechById(Long facilitySiteId, Long techId) {		//findTechById() method
        Tech tech = techDao.findById(techId).orElse(null);
	    if (tech == null) {
		throw new NoSuchElementException("Tech with ID=" + techId + " does not exist.");
     	}
    	if (!tech.getTechId().equals(facilitySiteId)) {
		throw new IllegalArgumentException(
     	"Tech with ID=" + techId + " does not exist in facility site with ID=" + facilitySiteId);}
	    return tech;

     }
        //////findOrCreateTech Method////
        public Tech findOrCreateTech(Long facilitySiteId, Long techId) {
	    if (Objects.isNull(techId)) {
	    return new Tech();
    	}
	    return findTechById(facilitySiteId, techId);
     }
        
        	////copyTechFields method///////
	       	public void copyTechFields(Tech tech, FacilitySiteTech facilitySiteTech) {
	    	tech.setTechId(facilitySiteTech.getTechId());
	    	tech.setTechName(facilitySiteTech.getTechName());
	    	tech.setTechRole(facilitySiteTech.getTechRole());

	      }
	       /////findFacilitySiteById////
	       	
		    @Transactional(readOnly = false)
		    public FacilitySitePatient savePatient(Long facilitySiteId, FacilitySitePatient facilitySitePatient) {
			FacilitySite facilitySite = findFacilitySiteById(facilitySiteId);
			Long patientId = facilitySitePatient.getPatientId();
			Patient patient = findOrCreatePatient(facilitySiteId, patientId);
			copyPatientFields(patient, facilitySitePatient);
			patient.getFacilitySites().add(facilitySite);
			facilitySite.getPatients().add(patient);
			Patient dbPatient = patientDao.save(patient);
			return new FacilitySitePatient(dbPatient);
		    }   
		    
		    
		    ///////findOrCreatePatient method////////
	 	    private Patient findOrCreatePatient(Long facilitySiteId, Long patientId) {
		 	if (Objects.isNull(patientId)) {
	     	return new Patient();
			} return findPatientById(facilitySiteId, patientId);
			
	    	}
	 	    
	 	    //////findPatientById method///////
		    private Patient findPatientById(Long facilitySiteId, Long patientId) {
			Patient patient = patientDao.findById(patientId).orElse(null);
			if (patient == null) {
			throw new NoSuchElementException("Patient with ID=" + patientId + " does not exist.");
			}
			boolean facilitySiteFound = patient.getFacilitySites().stream()
			.anyMatch(facilitySite -> facilitySite.getFacilitySiteId().equals(facilitySiteId));
			if (!facilitySiteFound) {
			throw new IllegalArgumentException(
      		"Facility Site with ID=" + facilitySiteId + " not found in patient's facility site.");
			}
		    return patient;
		    }
	 	    
		    //////////copyPatientFields/////////
		    private void copyPatientFields(Patient patient, FacilitySitePatient facilitySitePatient) {
		 	patient.setPatientId(facilitySitePatient.getPatientId());
			patient.setPatientName(facilitySitePatient.getPatientName());
			patient.setPatientDOB(facilitySitePatient.getPatientDOB());
			patient.setPatientGender(facilitySitePatient.getPatientGender());
			patient.setPatientExamPerformed(facilitySitePatient.getPatientExamPerformed());
			
		    }
	 	    
		  ////////////////retrieveAllFacilitySites///////////////
	     	@Transactional(readOnly = true)
	     	public List<XrayFacilityData> retrieveAllFacilitySites() {
	  	    List<FacilitySite> facilitySites = facilitySiteDao.findAll();
		    List<XrayFacilityData> result = new LinkedList<>();
		    for (FacilitySite facilitySite : facilitySites) {
		    	XrayFacilityData psd = new XrayFacilityData(facilitySite);
		        psd.getPatients().clear();
		        psd.getTechs().clear();
		        result.add(psd);
		        }
		        return result;
		        }
	 	    
	     	///////////////////retrieveFacilitySiteById/////////////
			@Transactional(readOnly = true)
			public XrayFacilityData retrieveFacilitySiteById(Long facilitySiteId) {
				FacilitySite facilitySite = facilitySiteDao.findById(facilitySiteId).orElseThrow(() -> new NoSuchElementException("Facility Site with ID=" + facilitySiteId + " does not exist."));
			    return new XrayFacilityData(facilitySite);
			}   
	 	    
			//////////////////deleteFacilitySiteById//////////////////
			@Transactional(readOnly = false)
			public void deleteFacilitySiteById(Long facilitySiteId) {
	      	FacilitySite facilitySite = facilitySiteDao.findById(facilitySiteId).orElseThrow(() -> new NoSuchElementException("Facility Site with ID=" + facilitySiteId + " does not exist."));
	        facilitySiteDao.delete(facilitySite);

			}


			public FacilitySitePatient savePatient(FacilitySitePatient facilitySitePatient) {
				// TODO Auto-generated method stub
				return null;
			}
	 	    
	 	    
}
