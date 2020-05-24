package interviewluty2020.listaprzebojow.model;

import interviewluty2020.listaprzebojow.model.enums.Genre;
import lombok.*;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "songs")
public class Song
{
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String author;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @OneToMany(mappedBy = "song")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Vote> votes;
}
