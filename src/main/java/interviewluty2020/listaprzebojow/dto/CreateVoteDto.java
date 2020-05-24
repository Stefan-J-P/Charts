package interviewluty2020.listaprzebojow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateVoteDto
{
    private Long id;
    private LocalDateTime voteDate;
    private CreateUserDto createUserDto;
    private CreateSongDto createSongDto;
}
