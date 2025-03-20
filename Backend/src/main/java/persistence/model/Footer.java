package persistence.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "footer")
public class Footer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String header;
    private String description;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
}
