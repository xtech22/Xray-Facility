package xray.facility.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import xray.facility.model.XrayFacilityData;
import xray.facility.service.FacilitySiteService;

@RestController
@Slf4j
public class TechController {
	
	
	
	
	@Autowired
    private FacilitySiteService facilitySiteService;
	
	@PutMapping("/tech/{techId}")
    public XrayFacilityData updateTech(@PathVariable String techId,
    @RequestBody XrayFacilityData xrayFacilityData) {
    FacilitySiteData.setTechId(techId);
    log.info("Updating tech {}", xrayFacilityData);	
    return facilitySiteService.saveFacilitySite(xrayFacilityData);
        	}

}
