package bedemo.service.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@Entity
//@AllArgsConstructor
//@NoArgsConstructor
public class Werknemer {
	@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	//@SequenceGenerator(name = "sequenceGenerator")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String naam;
	private String adres;
	private String woonplaats;
	private LocalDate geboorteDatum;
	
	/* Full-time salaris */
	private Double voltijdSalaris;
	/* Parttime percentage */
	private Double deeltijdPercentage;
	

	
	@ManyToOne
    @JsonIgnoreProperties("werknemers")
    private Werkgever werkgever;
	
	public Long getWerkgever() {
		return this.werkgever.getId();
	}
	
}
