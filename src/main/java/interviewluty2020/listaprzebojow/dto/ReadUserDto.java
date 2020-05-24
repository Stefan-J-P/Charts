package interviewluty2020.listaprzebojow.dto;

import interviewluty2020.listaprzebojow.model.Vote;
import interviewluty2020.listaprzebojow.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReadUserDto
{
    private Long id;

    private String username;
    private String password;

    private String name;
    private String surname;
    private String email;
    private Integer age;

    private Role role;

    private Set<ReadVoteDto> readVoteDtos;

}
