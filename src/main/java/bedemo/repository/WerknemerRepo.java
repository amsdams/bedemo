package bedemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bedemo.service.domain.Werkgever;
import bedemo.service.domain.Werknemer;



@SuppressWarnings("unused")
@Repository
public interface WerknemerRepo extends JpaRepository<Werknemer, Long> {
	  List<Werknemer> findByWerkgever(Long id);

	List<Werknemer> findByWerkgeverId(Long id);

}
