package xray.facility.entity;
import jakarta.persistence.Entity;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Entity														//specifies persistent entity in database
@Data														//generates boiler plate code for getters setters equals hashCode
public class Patient {
	@Id //primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)		//value of identity will be auto generated
	private Long patientId;									//unique identifier for patient
	private String patientName;
	private String patientDOB;
	private String patientGender;
	private String patientExamPerformed;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "patients", cascade = CascadeType.PERSIST)	//shows patients field is owning side
	Set<FacilitySite> facilitySites = new HashSet<>();
}
