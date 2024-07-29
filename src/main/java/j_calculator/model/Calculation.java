package j_calculator.model;

import java.time.LocalDateTime;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Calculation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    private String operation;
    @Getter @Setter
    private double operand1;
    @Getter @Setter
    private double operand2;
    @Getter @Setter
    private double result;

    @Setter
    @ManyToOne
    @JoinColumn(name = "session_id")
    private CalculationSession session;

     public Calculation(String operation, double operand1, double operand2, double result, 
                                                            String session) {
        System.out.println("Calculation create started!");                                                       
        this.operation = operation;
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.session.setSessionId(session);
        System.out.println("Calculation create complete!");
    }
}
