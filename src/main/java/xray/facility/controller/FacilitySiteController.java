package xray.facility.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import xray.facility.entity.Tech;
import xray.facility.model.XrayFacilityData;
import xray.facility.model.XrayFacilityData.FacilitySitePatient;
import xray.facility.model.XrayFacilityData.FacilitySiteTech;
import xray.facility.service.FacilitySiteService;

@RestController
@Slf4j
public class FacilitySiteController {
	/* Source: @Promineo...
    @RestController – This tells Spring that this class is a REST controller. As such it expects and returns JSON in the request/response bodies. 
  The default response status code is 200 (OK) if you don't specify something different. And finally, this annotation tells Spring to map HTTP 
  requests to class methods. The annotation is in the org.springframework.web.bind.annotation package.
	 @RequestMapping("/clinic_site") – This tells Spring that the URI for every HTTP request that is mapped to a method in 
	 this controller class must start with "/clinic_site". This annotation is in the org.springframework.web.bind.annotation package.
  @Slf4j – This is a Lombok annotation that creates an SLF4J logger. It adds the logger as an instance variable named log.
   Use it like this:
 	 log.info("This is a log line"): 
 	*/ 
	
	@Autowired
	private FacilitySiteService facilitySiteService;
	
	@PostMapping("/facility_site")
    @ResponseStatus(code = HttpStatus.CREATED)
    public XrayFacilityData createFacilitySite(@RequestBody XrayFacilityData xrayFacilityData) {log.info("Creating facility site{}", xrayFacilityData);		
 	  return facilitySiteService.saveFacilitySite(xrayFacilityData);

    }
	
	@PutMapping("/facility_site/{facilitySiteId}")
    public XrayFacilityData updateFacilitySite(@PathVariable Long facilitySiteId, @RequestBody XrayFacilityData xrayFacilityData) {
		xrayFacilityData.setFacilitySiteId(facilitySiteId); log.info("Updating facility site{}", xrayFacilityData);	
	   return facilitySiteService.saveFacilitySite(xrayFacilityData);
     }
	
	 @PostMapping("/facility_site/{facilitySiteId}/patient")
     @ResponseStatus(code = HttpStatus.CREATED)
     public FacilitySitePatient addPatient(@PathVariable Long facilitySiteId, 
     @RequestBody FacilitySitePatient facilitySitePatient) {	
     log.info("Creating facility site patient {}", facilitySitePatient);			
      FacilitySitePatient patient = facilitySiteService.savePatient(facilitySiteId, facilitySitePatient);	
      return patient;
      }
	 
	 
	 
	 /////////////////////Tech Entity/////////////////
	 @PostMapping("/facility_site/{facilitySiteId}/tech")
     @ResponseStatus(code = HttpStatus.CREATED)
     public FacilitySiteTech addTech(@PathVariable Long facilitySiteId, 
     @RequestBody FacilitySiteTech facilitySiteTech) {	
     log.info("Creating facility site tech {}", facilitySiteTech);			
      FacilitySiteTech tech = facilitySiteService.saveTech(facilitySiteId, facilitySiteTech);	
      return tech;
       }
	
	 @PutMapping("/facility_site/{facilitySiteId}/tech/{techId}")
     public XrayFacilityData updateTech(@PathVariable Long techId,
     @RequestBody XrayFacilityData xrayFacilityData) {
		 		Tech.setTechId(techId);
     log.info("Updating tech {}", xrayFacilityData);	
     return facilitySiteService.saveFacilitySite(xrayFacilityData);
   	}
	 
	
	 
	 
	 /////////////////////Facility Entity/////////////////
	 @GetMapping("/facility_site")
     public List<XrayFacilityData> getAllFacilitySites() {
  	  log.info("Retrieving all facility sites");
     List<XrayFacilityData> facilitySites = facilitySiteService.retrieveAllFacilitySites();
     return facilitySites;
     }
	
	 @GetMapping("/facility_site/{facilitySiteId}")
     public XrayFacilityData getFacilitySiteById(@PathVariable Long facilitySiteId) {
	       log.info("Retrieving facility site with ID= {}", facilitySiteId);
	       XrayFacilityData facilitySite = facilitySiteService.retrieveFacilitySiteById(facilitySiteId);
            return facilitySite;
          }
	 
	 @DeleteMapping("/facility_site/{facilitySiteId}/techs")public Map<String, String> deleteFacilitySiteById(@PathVariable Long facilitySiteId) {
         log.info("Deleting facility site with ID={}", facilitySiteId);
      facilitySiteService.deleteFacilitySiteById(facilitySiteId);
      return Map.of("message", "Delete facility site with ID=" + facilitySiteId + " was successful!");
       }
	
	
	
	
	
	
	
	
	
	
	
}
