package interviewluty2020.listaprzebojow.service;


import interviewluty2020.listaprzebojow.dto.CreateSongDto;
import interviewluty2020.listaprzebojow.dto.Mappers;
import interviewluty2020.listaprzebojow.dto.ReadSongDto;
import interviewluty2020.listaprzebojow.exception.AppException;
import interviewluty2020.listaprzebojow.model.Song;
import interviewluty2020.listaprzebojow.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SongService
{
    private final SongRepository songRepository;

    public void add(CreateSongDto createSongDto)
    {
        if (createSongDto == null)
        {
            throw new AppException("CREATE SONG DTO IS NULL");
        }

        Song song = Mappers.fromCreateSongDtoToSong(createSongDto);
        songRepository.save(song);
    }

    public List<ReadSongDto> getAll()
    {
        return songRepository
                .findAll()
                .stream()
                .map(Mappers::fromSongToReadSongDto)
                .collect(Collectors.toList());
    }

    public ReadSongDto getOne(Long id)
    {
        if (id == null)
        {
            throw new AppException("ID IS NULL");
        }
        return songRepository
                .findById(id)
                .map(Mappers::fromSongToReadSongDto)
                .orElseThrow(() -> new AppException("CANNOT FIND SONG WITH ID: " + id));
    }

    public void update(ReadSongDto readSongDto)
    {
        if (readSongDto == null)
        {
            throw new AppException("READ SONG DTO OBJECT IS NULL");
        }

        songRepository
                .findById(readSongDto.getId())
                .ifPresent(song -> {
                    song.setAuthor(readSongDto.getAuthor() == null ? song.getAuthor() : readSongDto.getAuthor());
                    song.setTitle(readSongDto.getTitle() == null ? song.getTitle() : readSongDto.getTitle());
                    song.setGenre(readSongDto.getGenre() == null ? song.getGenre() : readSongDto.getGenre());
                    //song.setVotes();
                });
    }

    public void delete(Long id)
    {
        if (id == null)
        {
            throw new AppException("ID IS NULL");
        }
        songRepository.deleteById(id);
    }



}





