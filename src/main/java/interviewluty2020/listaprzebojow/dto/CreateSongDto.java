package interviewluty2020.listaprzebojow.dto;

import interviewluty2020.listaprzebojow.model.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateSongDto
{
    private Long id;

    private String title;
    private String author;

    private Genre genre;
}
