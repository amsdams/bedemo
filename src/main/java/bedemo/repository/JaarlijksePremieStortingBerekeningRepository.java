package bedemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bedemo.service.domain.JaarlijksePremieStortingBerekening;

;

@SuppressWarnings("unused")
@Repository
public interface JaarlijksePremieStortingBerekeningRepository extends JpaRepository<JaarlijksePremieStortingBerekening, Long> {

}