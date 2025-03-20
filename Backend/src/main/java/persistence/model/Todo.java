package persistence.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, updatable = false)
    private String date;
    private String description;
    private boolean status;

    @Column(nullable = false, updatable = false)
    private String done_by = "";

    @PreUpdate
    protected void onUpdate() {
        this.done_by = getCurrentUser();
    }

    private String getCurrentUser() {
        return account.getUsername();
    }

    @PrePersist
    protected void onCreate() {
        this.date = java.time.LocalDate.now().toString();
    }

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
}
