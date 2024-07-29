package j_calculator.repository;

import j_calculator.model.Calculation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculationRepository extends JpaRepository<Calculation, Long> {
}
