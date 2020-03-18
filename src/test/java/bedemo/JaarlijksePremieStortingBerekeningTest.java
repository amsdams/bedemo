package bedemo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import bedemo.service.JaarlijksePremieStortingBeerekeningService;
import bedemo.service.domain.JaarlijksePremieStortingBerekening;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class JaarlijksePremieStortingBerekeningTest {
	@Autowired
	private JaarlijksePremieStortingBeerekeningService jaarlijksePremieStortingBeerekeningServiceImpl;

	@Test
	void testJaarlijksePremieStortingResultaat() {
		JaarlijksePremieStortingBerekening jaarlijksePremieStortingBerekening = new JaarlijksePremieStortingBerekening();
		jaarlijksePremieStortingBerekening.setVoltijdSalaris(40000d);
		jaarlijksePremieStortingBerekening.setDeeltijdPercentage(100d);
		jaarlijksePremieStortingBerekening.setVoltijdFranchise(13785d);
		jaarlijksePremieStortingBerekening.setPremiePercentage(1.657);

		Double jaarlijksePremieStortingResultaat = jaarlijksePremieStortingBeerekeningServiceImpl
				.getJaarlijksePremieStorting(jaarlijksePremieStortingBerekening);

		log.debug("jaarlijksePremieStortingResultaat: {}", jaarlijksePremieStortingResultaat);
		assertThat(jaarlijksePremieStortingResultaat).isEqualTo(434.38255000000004);

		jaarlijksePremieStortingBerekening.setJaarlijksePremieStortingResultaat(jaarlijksePremieStortingResultaat);

		assertThat(jaarlijksePremieStortingBerekening.getJaarlijksePremieStortingResultaat()).isEqualTo(434.38255000000004);
	}

	@Test
	void testJaarlijksePremieStortingResultaatUpdated() {
		JaarlijksePremieStortingBerekening jaarlijksePremieStortingBerekening = new JaarlijksePremieStortingBerekening();
		jaarlijksePremieStortingBerekening.setVoltijdSalaris(40000d);
		jaarlijksePremieStortingBerekening.setDeeltijdPercentage(100d);
		jaarlijksePremieStortingBerekening.setVoltijdFranchise(15599d);
		jaarlijksePremieStortingBerekening.setPremiePercentage(5d);

		Double jaarlijksePremieStortingResultaat = jaarlijksePremieStortingBeerekeningServiceImpl
				.getJaarlijksePremieStorting(jaarlijksePremieStortingBerekening);

		log.debug("jaarlijksePremieStortingResultaat: {}", jaarlijksePremieStortingResultaat);
		assertThat(jaarlijksePremieStortingResultaat).isEqualTo(1220.05);

		jaarlijksePremieStortingBerekening.setJaarlijksePremieStortingResultaat(jaarlijksePremieStortingResultaat);

		assertThat(jaarlijksePremieStortingBerekening.getJaarlijksePremieStortingResultaat()).isEqualTo(1220.05);

	}
}
