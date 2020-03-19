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

import bedemo.repository.RegelingRepo;
import bedemo.service.domain.Regeling;
import bedemo.service.domain.Werknemer;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
@CrossOrigin
public class RegelingResource {

	private final RegelingRepo regelingRepo;

	public RegelingResource(RegelingRepo regelingRepo) {
		this.regelingRepo = regelingRepo;
	}

	

	@GetMapping("/regelingen")
	public ResponseEntity<List<Regeling>> getAllRegeling() {

		List<Regeling> regelingen = regelingRepo.findAll();
		return ResponseEntity.ok().body(regelingen);
	}


	@GetMapping("/regelingen/search/findByWerkgever/{id}")
	public ResponseEntity<List<Regeling>> getRegelingenByWerkgeverId(@PathVariable Long id) {

		List<Regeling> regelingen = regelingRepo.findAll().stream().filter(predicate->predicate.getWerkgever().equals(id)).collect(Collectors.toList()); ;
		return ResponseEntity.ok().body(regelingen);
	}
	
	@GetMapping("/regelingen/{id}")
	public ResponseEntity<Regeling> getRegeling(@PathVariable Long id) {

		Optional<Regeling> regelingen = regelingRepo.findById(id);
		if (!regelingen.isPresent()) {
			ResponseEntity.notFound();
		}
		return ResponseEntity.ok(regelingen.get());
	}
}
