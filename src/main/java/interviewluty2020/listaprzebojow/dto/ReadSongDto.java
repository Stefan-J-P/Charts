package interviewluty2020.listaprzebojow.dto;

import interviewluty2020.listaprzebojow.model.Vote;
import interviewluty2020.listaprzebojow.model.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReadSongDto
{
    private Long id;
    private String title;
    private String author;

    private Genre genre;
    private Set<ReadVoteDto> readVoteDtos;
}
