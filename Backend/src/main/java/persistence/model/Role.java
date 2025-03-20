package persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "role")

public class Role {
    @Id
    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<Header> headers;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<Footer> footers;
}
