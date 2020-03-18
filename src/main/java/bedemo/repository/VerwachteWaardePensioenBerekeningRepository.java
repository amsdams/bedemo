package bedemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bedemo.service.domain.JaarlijksePremieStortingBerekening;
import bedemo.service.domain.VerwachteWaardePensioenBerekening;

;

@SuppressWarnings("unused")
@Repository
public interface VerwachteWaardePensioenBerekeningRepository extends JpaRepository<VerwachteWaardePensioenBerekening, Long> {

}