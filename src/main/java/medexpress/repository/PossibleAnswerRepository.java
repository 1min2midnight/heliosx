package medexpress.repository;

import medexpress.model.PossibleAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PossibleAnswerRepository extends JpaRepository<PossibleAnswer, Long> {
}
