package xray.facility.model;


import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import xray.facility.entity.FacilitySite;
import xray.facility.entity.Patient;
import xray.facility.entity.Tech;



@Data
@NoArgsConstructor
public class XrayFacilityData {							//constructor for XrayFacilityData takes FacilitySite as parameter. sets matching fields in the FacilitySiteData class to data in the FacilitySite class
	  private Long   facilitySiteId;	  
	  private String facilitySiteName;
	  private String facilitySiteModality;
	  private Set<Patient> patients = new HashSet<>();
	  private Set<Tech> techs = new HashSet<>();
	  
	  																
      public XrayFacilityData(FacilitySite facilitySite) {
	  this.facilitySiteId = facilitySite.getFacilitySiteId();
	  this.facilitySiteName = facilitySite.getFacilitySiteName();
	  this.facilitySiteModality = facilitySite.getFacilitySiteModality();
	  																     
	  	this.techs = new HashSet<>();
	
	  	for(Tech tech : facilitySite.getTechs()) {
		  this.techs.add(tech);
		  }														//Sets the techs and patients fields to the respective FacilitySitePatient and FacilitySiteTech objects. These are Sets so used loops
	  	for(Patient patient : facilitySite.getPatients()) {
		  this.patients.add(patient);
		  }
	  
      }  
      
   
      
	  @Data
      
	  public static class FacilitySitePatient {
		  															//Constructor it takes Patient object gets corresponding fields in FacilitySitePatient instance
      public FacilitySitePatient(Patient patient) { 
	   patientId = patient.getPatientId();
	   patientName = patient.getPatientName();
	   patientDOB = patient.getPatientDOB();
	   patientGender = patient.getPatientGender();
	   patientExamPerformed = patient.getPatientExamPerformed();
      }
      
      																		//end FacilitySitePatient  constructor class
      																		//the following is fields from patient entity
      private Long   patientId;
      private String patientName;
      private String patientDOB;
      private String patientGender;
  	  private String patientExamPerformed;
      }
	
 
	  @Data
	  @NoArgsConstructor 									
	  public static class FacilitySiteTech {
		  		private Long  techId;
  				private String techName;
  				private String techRole;	
  				
  				//constructor takes Tech object gets the corresponding fields in FacilitySiteTech instance
	    		public FacilitySiteTech(Tech tech) {
	    			techId = tech.getTechId();
	    			techName = tech.getTechName();
	    			techRole = tech.getTechRole();
	    			
	    			
	    		}		
	    			
	  }



}   		    			
	    
		    		
	    		
	    		
	    	
	        
    
	

 
	  
        
	  
	  
	  
	  
	  
	  
	  
	  

