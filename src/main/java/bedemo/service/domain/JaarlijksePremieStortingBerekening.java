package bedemo.service.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Data
@Entity
public class JaarlijksePremieStortingBerekening {

	@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	//@SequenceGenerator(name = "sequenceGenerator")
	@GeneratedValue
	private Long id;

	/* Full-time salaris */
	private Double voltijdSalaris;
	/* Parttime percentage */
	private Double deeltijdPercentage;
	/* Franchise in de regeling (voor nu: vastgesteld op 15.599, -) */
	private Double voltijdFranchise;
	/* Beschikbare premie percentage (voor nu: vast percentage van 5%) */
	private Double premiePercentage;

	private Double jaarlijksePremieStortingResultaat;
}
