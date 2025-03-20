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
@Table(name = "account")

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_name", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Todo> todos;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Information> information;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Game> games;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<QA> qas;
}
