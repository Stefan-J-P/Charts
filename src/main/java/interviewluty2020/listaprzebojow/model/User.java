package interviewluty2020.listaprzebojow.model;

import interviewluty2020.listaprzebojow.model.enums.Role;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String password;

    private String name;
    private String surname;
    private String email;
    private Integer age;

    private Boolean enabled;

    @Enumerated
    private Role role;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Vote> votes;
}
