package bedemo.service.impl;

import org.springframework.stereotype.Service;

import bedemo.service.VerwachteWaardePensioenBerekeningService;
import bedemo.service.domain.VerwachteWaardePensioenBerekening;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VerwachteWaardePensioenBerekeningServiceImpl implements VerwachteWaardePensioenBerekeningService {
	
	/*
	 * Huidige waarde + Jaarlijkse premiestorting + (Huidige waarde + Jaarlijkse
	 * premiestorting/2) * rendement
	 */
	@Override
	public Double getVerwachteWaardePensioen(VerwachteWaardePensioenBerekening verwachteWaardePensioenBerekening) {

		Integer jarenTotPensioen = verwachteWaardePensioenBerekening.getGewenstePensioenLeeftijdDeelnemer()
				- verwachteWaardePensioenBerekening.getHuidigeLeeftijdDeelnemer();

		log.info("jarenTotPensioen : {} ", jarenTotPensioen);
		Double nieuweWaardeBeleggingen = verwachteWaardePensioenBerekening.getHuidigeWaardeBeleggingen();
		
		for (int i = 0; i < jarenTotPensioen; i++) {
			nieuweWaardeBeleggingen = getHuidgeWaarde(verwachteWaardePensioenBerekening, nieuweWaardeBeleggingen);
			log.info("huidigeWaardeBeleggingen: {} {}", i, nieuweWaardeBeleggingen);
		}
		return nieuweWaardeBeleggingen;

	}
	/*
	 * Huidige waarde + Jaarlijkse premiestorting + (Huidige waarde + Jaarlijkse
	 * premiestorting/2) * rendement
	 */

	private Double getHuidgeWaarde(VerwachteWaardePensioenBerekening verwachteWaardePensioenBerekening, Double nieuweWaardeBeleggingen) {
		return nieuweWaardeBeleggingen
				+ verwachteWaardePensioenBerekening.getJaarlijksePremieStorting()
				+ (nieuweWaardeBeleggingen
						+ verwachteWaardePensioenBerekening.getJaarlijksePremieStorting() / 2)
						* ((verwachteWaardePensioenBerekening.getJaarlijksRendementBeleggingen() / 100));

	}
}
