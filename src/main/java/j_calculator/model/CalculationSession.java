package j_calculator.model;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Entity
public class CalculationSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @Column(unique = true)
    private String sessionId;
    
    @Getter @Setter
    private boolean active;

    @Getter @Setter
    private LocalDateTime createdAt;

    @Getter @Setter
    private LocalDateTime updatedAt;

    @Getter
    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    private List<Calculation> calculations = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}