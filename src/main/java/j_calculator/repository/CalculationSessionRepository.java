package j_calculator.repository;

import j_calculator.model.CalculationSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculationSessionRepository extends JpaRepository<CalculationSession, Long> {
    CalculationSession findBySessionId(String sessionId);
}