package xray.facility.entity;
import java.util.Set;
import java.util.HashSet;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class FacilitySite {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long   facilitySiteId;	  
	  private String facilitySiteName;
	  private String facilitySiteModality;
	  
	  @EqualsAndHashCode.Exclude
	  @ToString.Exclude
	  @ManyToMany(cascade = CascadeType.PERSIST)
	  @JoinTable(name = "facility_site_patient",
	  joinColumns = @JoinColumn(name = "facility_site_id"),
	  inverseJoinColumns = @JoinColumn(name = "patient_id"))
	  Set<Patient> patients = new HashSet<>();
	  
	  @EqualsAndHashCode.Exclude
	  @ToString.Exclude
	  @OneToMany(mappedBy = "facilitySite", cascade = CascadeType.ALL, orphanRemoval = true)
	  Set<Tech> techs = new HashSet<>();
	  
	  
}
