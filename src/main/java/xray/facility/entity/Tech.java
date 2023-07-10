package xray.facility.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity// specifies persistent entity in database
@Data//....generates boiler plate code for getters setters equals hashCode
public class Tech {
	@Id //primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)//...value of identity will be auto generated
	static Long techId; //...unique identifier for patient
	
	private String techName;
	private String techRole;
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "facility_site_id")  
	FacilitySite facilitySite;
	
	
	

}
