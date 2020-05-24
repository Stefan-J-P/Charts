package interviewluty2020.listaprzebojow.service;

import interviewluty2020.listaprzebojow.dto.ReadSongDto;
import interviewluty2020.listaprzebojow.model.Song;
import interviewluty2020.listaprzebojow.repository.SongRepository;
import interviewluty2020.listaprzebojow.repository.UserRepository;
import interviewluty2020.listaprzebojow.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class StatisticsService
{
    private final SongRepository songRepository;
    private final UserRepository userRepository;
    private final VoteRepository voteRepository;

    // a) pokazuje najlpopularniejszych utworow
/*    public List<Song> numberOfMostPopularSongs(int z)
    {
        return songRepository
                .findAll()
                .stream()
                .map(Song::getVotes)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Song::getVotes), Collectors.counting())
                .entrySet
                ))
    }*/




}
