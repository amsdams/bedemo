package bedemo.service.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@Entity
//@AllArgsConstructor
//@NoArgsConstructor
public class Werkgever {
	@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	//@SequenceGenerator(name = "sequenceGenerator")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String naam;

	@OneToMany(mappedBy = "werkgever")
	@ToString.Exclude
    private Set<Werknemer> werknemers = new HashSet<>();

    @OneToMany(mappedBy = "werkgever")
    @ToString.Exclude
    private Set<Regeling> regelings = new HashSet<>();
    
	
    
}
