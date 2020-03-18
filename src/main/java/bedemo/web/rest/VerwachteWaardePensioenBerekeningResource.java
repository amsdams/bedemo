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

import bedemo.repository.VerwachteWaardePensioenBerekeningRepository;
import bedemo.service.VerwachteWaardePensioenBerekeningService;
import bedemo.service.domain.VerwachteWaardePensioenBerekening;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
@CrossOrigin
public class VerwachteWaardePensioenBerekeningResource {

	private final VerwachteWaardePensioenBerekeningService verwachteWaardePensioenBerekeningServiceImpl;
	private final VerwachteWaardePensioenBerekeningRepository verwachteWaardePensioenBerekeningRepository;

	public VerwachteWaardePensioenBerekeningResource(VerwachteWaardePensioenBerekeningService verwachteWaardePensioenBerekeningServiceImpl,
			VerwachteWaardePensioenBerekeningRepository verwachteWaardePensioenBerekeningRepository) {
		this.verwachteWaardePensioenBerekeningServiceImpl = verwachteWaardePensioenBerekeningServiceImpl;
		this.verwachteWaardePensioenBerekeningRepository = verwachteWaardePensioenBerekeningRepository;
	}

	@PostMapping("/verwachtewaardepensioenberekeningen")
	public ResponseEntity<VerwachteWaardePensioenBerekening> createVerwachteWaardePensioenBerekening(@Valid @RequestBody VerwachteWaardePensioenBerekening verwachteWaardePensioenBerekening) throws URISyntaxException {
		
		if (verwachteWaardePensioenBerekening.getId()!=null) {
			return ResponseEntity.badRequest().build();
		}
		
		if (verwachteWaardePensioenBerekening.getVerwachteWaardePensioenResultaat()!=null) {
			return ResponseEntity.badRequest().build();
		}
		
		Double verwachteWaardePensioenResultaat =  verwachteWaardePensioenBerekeningServiceImpl.getVerwachteWaardePensioen(verwachteWaardePensioenBerekening);
		log.debug("verwachteWaardePensioenResultaat {}", verwachteWaardePensioenResultaat);
		verwachteWaardePensioenBerekening.setVerwachteWaardePensioenResultaat(verwachteWaardePensioenResultaat);
		
		VerwachteWaardePensioenBerekening result= verwachteWaardePensioenBerekeningRepository.save(verwachteWaardePensioenBerekening);
		return ResponseEntity.created(new URI("/api/verwachtewaardepensioenberekeningen/" + result.getId())).body(result);
	}
	
	@GetMapping("/verwachtewaardepensioenberekeningen")
	public ResponseEntity<List<VerwachteWaardePensioenBerekening>> getAllJaarlijksePremieStortingBerekeningen() {

		List<VerwachteWaardePensioenBerekening> jaarlijksePremieStortings = verwachteWaardePensioenBerekeningRepository.findAll();
		return ResponseEntity.ok().body(jaarlijksePremieStortings);
	}


	@GetMapping("/verwachtewaardepensioenberekeningen/{id}")
	public ResponseEntity<VerwachteWaardePensioenBerekening> getJaarlijksePremieStortingBerekening(@PathVariable Long id) {

		Optional<VerwachteWaardePensioenBerekening> jaarlijksePremieStorting = verwachteWaardePensioenBerekeningRepository.findById(id);
		if (!jaarlijksePremieStorting.isPresent()) {
			ResponseEntity.notFound();
		}
		return ResponseEntity.ok(jaarlijksePremieStorting.get());
	}
}
