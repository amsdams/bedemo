package bedemo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import bedemo.service.VerwachteWaardePensioenBerekeningService;
import bedemo.service.domain.VerwachteWaardePensioenBerekening;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class VerwachteWaardePensioenBerekeningTest {
	@Autowired
	private VerwachteWaardePensioenBerekeningService verwachteWaardePensioenBerekeningServiceImpl;

	@Test
	void testVerwachteWaardePensioenResultaat() {

		Double jaarlijksePremieStortingResultaat = 434.38255000000004;
		VerwachteWaardePensioenBerekening verwachteWaardePensioenBerekening = new VerwachteWaardePensioenBerekening();
		verwachteWaardePensioenBerekening.setHuidigeLeeftijdDeelnemer(35);
		verwachteWaardePensioenBerekening.setGewenstePensioenLeeftijdDeelnemer(68);

		verwachteWaardePensioenBerekening.setHuidigeWaardeBeleggingen(100d);
		verwachteWaardePensioenBerekening.setJaarlijksePremieStorting(jaarlijksePremieStortingResultaat);
		verwachteWaardePensioenBerekening.setJaarlijksRendementBeleggingen(3d);

		Double verwachteWaardePensioenResultaat = verwachteWaardePensioenBerekeningServiceImpl
				.getVerwachteWaardePensioen(verwachteWaardePensioenBerekening);

		log.debug("verwachteWaardePensioenResultaat: {} ", verwachteWaardePensioenResultaat);
		assertThat(verwachteWaardePensioenResultaat).isEqualTo(24548.959464041167);

		verwachteWaardePensioenBerekening.setVerwachteWaardePensioenResultaat(verwachteWaardePensioenResultaat);

		assertThat(verwachteWaardePensioenBerekening.getVerwachteWaardePensioenResultaat()).isEqualTo(24548.959464041167);

	}

	@Test
	void testVerwachteWaardePensioenResultaatUpdated() {

		Double jaarlijksePremieStortingResultaat = 1220.05;

		VerwachteWaardePensioenBerekening verwachteWaardePensioenBerekening = new VerwachteWaardePensioenBerekening();
		verwachteWaardePensioenBerekening.setHuidigeLeeftijdDeelnemer(35);
		verwachteWaardePensioenBerekening.setGewenstePensioenLeeftijdDeelnemer(68);

		verwachteWaardePensioenBerekening.setHuidigeWaardeBeleggingen(100d);
		verwachteWaardePensioenBerekening.setJaarlijksePremieStorting(jaarlijksePremieStortingResultaat);
		verwachteWaardePensioenBerekening.setJaarlijksRendementBeleggingen(3d);

		Double verwachteWaardePensioenResultaat = verwachteWaardePensioenBerekeningServiceImpl
				.getVerwachteWaardePensioen(verwachteWaardePensioenBerekening);

		log.debug("verwachteWaardePensioenResultaat: {}", verwachteWaardePensioenResultaat);
		assertThat(verwachteWaardePensioenResultaat).isEqualTo(68470.91957947443);

		verwachteWaardePensioenBerekening.setVerwachteWaardePensioenResultaat(verwachteWaardePensioenResultaat);

		assertThat(verwachteWaardePensioenBerekening.getVerwachteWaardePensioenResultaat()).isEqualTo(68470.91957947443);

	}
}
