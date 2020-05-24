package interviewluty2020.listaprzebojow.dto;

import interviewluty2020.listaprzebojow.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserDto
{
    private Long id;

    private String username;
    private String password;

    private String name;
    private String surname;
    private String email;
    private Integer age;

    private Role role;

}
/*
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
 */