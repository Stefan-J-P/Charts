package interviewluty2020.listaprzebojow.dto;

import interviewluty2020.listaprzebojow.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserDto
{
    private String username;

    private String password;
    private String passwordConfirmation;

    private String email;

    private Role role;

}
