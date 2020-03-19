package bedemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bedemo.service.domain.Werkgever;



@SuppressWarnings("unused")
@Repository
public interface WerkgeverRepo extends JpaRepository<Werkgever, Long> {

}