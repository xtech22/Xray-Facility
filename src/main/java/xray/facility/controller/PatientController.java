package xray.facility.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import xray.facility.model.XrayFacilityData;
import xray.facility.service.FacilitySiteService;
@RestController
@Slf4j
public class PatientController {
	@Autowired
    private FacilitySiteService facilitySiteService;

 
  
    
    @GetMapping("/facility_site/patient/{patientId}")
   public List<XrayFacilityData> getAllFacilitySites() {
	  log.info("Retrieving all facility sites");
   List<XrayFacilityData> facilitySites = facilitySiteService.retrieveAllFacilitySites();
   return facilitySites;
   }

}
	
	
	
