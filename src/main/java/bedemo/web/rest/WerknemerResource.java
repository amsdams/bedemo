package bedemo.web.rest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bedemo.repository.WerknemerRepo;
import bedemo.service.domain.Werknemer;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
@CrossOrigin
public class WerknemerResource {

	private final WerknemerRepo werknemerRepo;

	public WerknemerResource(WerknemerRepo werkgeverRepo) {
		this.werknemerRepo = werkgeverRepo;
	}

	

	@GetMapping("/werknemers")
	public ResponseEntity<List<Werknemer>> getAllWerknemer() {

		List<Werknemer> werknemer = werknemerRepo.findAll();
		return ResponseEntity.ok().body(werknemer);
	}

	@GetMapping("/werknemers/search/findByWerkgever/{id}")
	public ResponseEntity<List<Werknemer>> getWerknemersByWerkgeverId(@PathVariable Long id) {

		List<Werknemer> werknemer = werknemerRepo.findAll().stream().filter(predicate->predicate.getWerkgever().equals(id)).collect(Collectors.toList()); ;
		return ResponseEntity.ok().body(werknemer);
	}
	

	@GetMapping("/werknemers/{id}")
	public ResponseEntity<Werknemer> getWerknemer(@PathVariable Long id) {

		Optional<Werknemer> werknemer = werknemerRepo.findById(id);
		if (!werknemer.isPresent()) {
			ResponseEntity.notFound();
		}
		return ResponseEntity.ok(werknemer.get());
	}
}
