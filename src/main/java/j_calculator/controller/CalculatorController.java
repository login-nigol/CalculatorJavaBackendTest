package j_calculator.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import j_calculator.model.Calculation;
import j_calculator.model.CalculationSession;
import j_calculator.service.CalculationService;

import java.io.Console;
import java.util.List;

@RestController
@RequestMapping("/api/j_calculator")
public class CalculatorController {

    @Autowired
    private CalculationService calculationService;
    
    @GetMapping("/add")
    public String add(@RequestParam double a, @RequestParam double b, @RequestParam String session) {
        System.out.println("Add operation: " + session);
        Calculation newCalculation = new Calculation("+", a, b, a+b, session);
        System.out.println("Calculation created!");
        calculationService.saveCalculation(session, newCalculation);
        return String.valueOf(a + b);
    }

    @GetMapping("/multiply")
    public String multiply(@RequestParam double a, @RequestParam double b, @RequestParam String session) {
        calculationService.saveCalculation(session, new Calculation("*", a, b, a*b, session));
        return String.valueOf(a * b);
    }
    
    @GetMapping("/devide")
    public String devide(@RequestParam double a, @RequestParam double b, @RequestParam String session) {
        calculationService.saveCalculation(session, new Calculation("/", a, b, a/b, session));
        return String.valueOf(a / b);
    }

    @GetMapping("/subtract")
    public String substruct(@RequestParam double a, @RequestParam double b, @RequestParam String session) {
        System.out.println("SUBSTRACT: " + a + " - " + b);
        calculationService.saveCalculation(session, new Calculation("-", a, b, a-b, session));
        return String.valueOf(a - b);
    }

    @PostMapping("/session")
    public CalculationSession createSession() {
        return calculationService.createSession();
    }

    @GetMapping("/history/{sessionId}")
    public List<Calculation> getHistory(@PathVariable String sessionId) {
        return calculationService.getCalculations(sessionId);
    }

    @PostMapping("/reset")
    public void resetSession(@RequestBody String sessionId) {
        calculationService.closeSession(sessionId);
    }
}
