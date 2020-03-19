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
public class Regeling {

	@Id
	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
	// "sequenceGenerator")
	// @SequenceGenerator(name = "sequenceGenerator")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String naam;
	/* Franchise in de regeling (voor nu: vastgesteld op 15.599, -) */
	private Double voltijdFranchise;
	/* Beschikbare premie percentage (voor nu: vast percentage van 5%) */
	private Double premiePercentage;

	private Double jaarlijksRendementBeleggingen;
	
	@ManyToOne
	@JsonIgnoreProperties("regelings")
	private Werkgever werkgever;
	
	public Long getWerkgever() {
		return this.werkgever.getId();
	}
	
	
}
