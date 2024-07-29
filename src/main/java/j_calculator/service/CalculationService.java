package j_calculator.service;

import j_calculator.model.Calculation;
import j_calculator.model.CalculationSession;
import j_calculator.repository.CalculationRepository;
import j_calculator.repository.CalculationSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CalculationService {

    @Autowired
    private CalculationRepository calculationRepository;

    @Autowired
    private CalculationSessionRepository sessionRepository;

    public CalculationSession createSession() {
        CalculationSession session = new CalculationSession();
        session.setSessionId(UUID.randomUUID().toString());
        session.setActive(true);
        System.out.println("Save Sassion: " + session.getSessionId());
        return sessionRepository.save(session);
    }

    public Calculation saveCalculation(String sessionId, Calculation calculation) {
        System.out.println("Save Calculation by ID: " + sessionId);
        CalculationSession session = sessionRepository.findBySessionId(sessionId);
        if (session == null || !session.isActive()) {
            throw new IllegalArgumentException("Invalid or inactive session");
        }
        calculation.setSession(session);
        return calculationRepository.save(calculation);
    }

    public List<Calculation> getCalculations(String sessionId) {
        CalculationSession session = sessionRepository.findBySessionId(sessionId);
        if (session == null) {
            throw new IllegalArgumentException("Invalid session");
        }
        return session.getCalculations();
    }

    public void closeSession(String sessionId) {
        CalculationSession session = sessionRepository.findBySessionId(sessionId);
        if (session != null) {
            session.setActive(false);
            sessionRepository.save(session);
        }
    }
}
