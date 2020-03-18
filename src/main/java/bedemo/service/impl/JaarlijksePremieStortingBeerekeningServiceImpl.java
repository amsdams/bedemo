package bedemo.service.impl;


import org.springframework.stereotype.Service;

import bedemo.service.JaarlijksePremieStortingBeerekeningService;
import bedemo.service.domain.JaarlijksePremieStortingBerekening;

@Service
public class JaarlijksePremieStortingBeerekeningServiceImpl implements JaarlijksePremieStortingBeerekeningService {
	/*
	 * (Full-time salaris – Franchise) * Parttime percentage * Beschikbare premie
	 * percentage
	 */
	@Override
	public Double getJaarlijksePremieStorting(JaarlijksePremieStortingBerekening jaarlijksePremieStortingBerekening) {
		return (jaarlijksePremieStortingBerekening.getVoltijdSalaris() - jaarlijksePremieStortingBerekening.getVoltijdFranchise())
				* (jaarlijksePremieStortingBerekening.getDeeltijdPercentage() / 100)
				* (jaarlijksePremieStortingBerekening.getPremiePercentage() / 100);

	}

}
