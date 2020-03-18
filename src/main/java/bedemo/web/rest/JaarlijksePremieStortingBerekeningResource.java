package bedemo.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bedemo.repository.JaarlijksePremieStortingBerekeningRepository;
import bedemo.service.JaarlijksePremieStortingBeerekeningService;
import bedemo.service.domain.JaarlijksePremieStortingBerekening;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
@CrossOrigin
public class JaarlijksePremieStortingBerekeningResource {

	private final JaarlijksePremieStortingBeerekeningService jaarlijksePremieStortingBeerekeningServiceImpl;
	private final JaarlijksePremieStortingBerekeningRepository jaarlijksePremieStortingBerekeningRepository;

	public JaarlijksePremieStortingBerekeningResource(JaarlijksePremieStortingBeerekeningService jaarlijksePremieStortingBeerekeningServiceImpl,
			JaarlijksePremieStortingBerekeningRepository jaarlijksePremieStortingBerekeningRepository) {
		this.jaarlijksePremieStortingBeerekeningServiceImpl = jaarlijksePremieStortingBeerekeningServiceImpl;
		this.jaarlijksePremieStortingBerekeningRepository = jaarlijksePremieStortingBerekeningRepository;
	}

	@PostMapping("/jaarlijksepremiestortingberekeningen")
	public ResponseEntity<JaarlijksePremieStortingBerekening> createJaarlijksePremieStortingBerekening(
			@Valid @RequestBody JaarlijksePremieStortingBerekening jaarlijksePremieStortingBerekening) throws URISyntaxException {
		if (jaarlijksePremieStortingBerekening.getId() != null) {
			return ResponseEntity.badRequest().build();
		}

		if (jaarlijksePremieStortingBerekening.getJaarlijksePremieStortingResultaat() != null) {
			return ResponseEntity.badRequest().build();
		}

		Double jaarlijksePremieStortingResultaat = jaarlijksePremieStortingBeerekeningServiceImpl
				.getJaarlijksePremieStorting(jaarlijksePremieStortingBerekening);
		log.debug("jaarlijksePremieStortingResultaat {}", jaarlijksePremieStortingResultaat);

		jaarlijksePremieStortingBerekening.setJaarlijksePremieStortingResultaat(jaarlijksePremieStortingResultaat);
		JaarlijksePremieStortingBerekening result = jaarlijksePremieStortingBerekeningRepository.save(jaarlijksePremieStortingBerekening);
		return ResponseEntity.created(new URI("/api/jaarlijksepremiestortingberekeningen/" + result.getId())).body(result);
	}

	@GetMapping("/jaarlijksepremiestortingberekeningen")
	public ResponseEntity<List<JaarlijksePremieStortingBerekening>> getAllJaarlijksePremieStortingBerekeningen() {

		List<JaarlijksePremieStortingBerekening> jaarlijksePremieStortingBerekenings = jaarlijksePremieStortingBerekeningRepository.findAll();
		return ResponseEntity.ok().body(jaarlijksePremieStortingBerekenings);
	}


	@GetMapping("/jaarlijksepremiestortingberekeningen/{id}")
	public ResponseEntity<JaarlijksePremieStortingBerekening> getJaarlijksePremieStortingBerekening(@PathVariable Long id) {

		Optional<JaarlijksePremieStortingBerekening> employee = jaarlijksePremieStortingBerekeningRepository.findById(id);
		if (!employee.isPresent()) {
			ResponseEntity.notFound();
		}
		return ResponseEntity.ok(employee.get());
	}
}
