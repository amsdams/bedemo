package bedemo.service.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Data
@Entity
public class VerwachteWaardePensioenBerekening {

	@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	//@SequenceGenerator(name = "sequenceGenerator")
	@GeneratedValue
	private Long id;

	private Integer huidigeLeeftijdDeelnemer;
	private Integer gewenstePensioenLeeftijdDeelnemer;
	private Double huidigeWaardeBeleggingen;
	private Double jaarlijksePremieStorting;
	private Double jaarlijksRendementBeleggingen;
	
	private Double verwachteWaardePensioenResultaat;
}
