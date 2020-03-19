package bedemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bedemo.service.domain.Regeling;



@SuppressWarnings("unused")
@Repository
public interface RegelingRepo extends JpaRepository<Regeling, Long> {

}
