package bedemo.web.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bedemo.repository.WerkgeverRepo;
import bedemo.service.domain.Werkgever;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
@CrossOrigin
public class WerkgeverResource {

	private final WerkgeverRepo werkgeverRepo;

	public WerkgeverResource(WerkgeverRepo werkgeverRepo) {
		this.werkgeverRepo = werkgeverRepo;
	}

	

	@GetMapping("/werkgevers")
	public ResponseEntity<List<Werkgever>> getAllWerkgever() {

		List<Werkgever> werkgevers = werkgeverRepo.findAll();
		return ResponseEntity.ok().body(werkgevers);
	}


	@GetMapping("/werkgevers/{id}")
	public ResponseEntity<Werkgever> getWerkgever(@PathVariable Long id) {

		Optional<Werkgever> werkgever = werkgeverRepo.findById(id);
		if (!werkgever.isPresent()) {
			ResponseEntity.notFound();
		}
		return ResponseEntity.ok(werkgever.get());
	}
}
