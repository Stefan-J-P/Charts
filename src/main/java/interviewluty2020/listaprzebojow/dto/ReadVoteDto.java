package interviewluty2020.listaprzebojow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReadVoteDto
{
    private Long id;
    private LocalDateTime voteDate;
    private ReadUserDto readUserDto;
    private ReadSongDto readSongDto;
}
