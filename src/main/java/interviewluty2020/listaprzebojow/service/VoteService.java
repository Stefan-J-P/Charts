package interviewluty2020.listaprzebojow.service;

import interviewluty2020.listaprzebojow.dto.CreateVoteDto;
import interviewluty2020.listaprzebojow.dto.Mappers;
import interviewluty2020.listaprzebojow.dto.ReadVoteDto;
import interviewluty2020.listaprzebojow.exception.AppException;
import interviewluty2020.listaprzebojow.model.Vote;
import interviewluty2020.listaprzebojow.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class VoteService
{
    private final VoteRepository voteRepository;

    public void add(CreateVoteDto createVoteDto)
    {
        if (createVoteDto == null)
        {
            throw new AppException("CREATE VOTE DTO IS NULL");
        }

        Vote vote = Mappers.fromCreateVoteDtoToVote(createVoteDto);
        voteRepository.save(vote);
    }

    public List<ReadVoteDto> getAll()
    {
        return voteRepository
                .findAll()
                .stream()
                .map(Mappers::fromVoteToReadVoteDto)
                .collect(Collectors.toList());
    }

    public ReadVoteDto getOne(Long id)
    {
        return voteRepository
                .findById(id)
                .map(Mappers::fromVoteToReadVoteDto)
                .orElseThrow(() -> new AppException("CANNOT FIND VOTE WITH ID: " + id));
    }

    public void update(ReadVoteDto readVoteDto)
    {
        if (readVoteDto == null)
        {
            throw new AppException("READ VOTE DTO IS NULL");
        }
        voteRepository
                .findById(readVoteDto.getId())
                .ifPresent(vote -> {
                    vote.setSong(Mappers.fromReadSongDtoToSong(readVoteDto.getReadSongDto()) == null ? vote.getSong() : Mappers.fromReadSongDtoToSong(readVoteDto.getReadSongDto()));
                    vote.setUser(Mappers.fromReadUserDtoToUser(readVoteDto.getReadUserDto()) == null ? vote.getUser() : Mappers.fromReadUserDtoToUser(readVoteDto.getReadUserDto()));
                    vote.setVoteDate(readVoteDto.getVoteDate() == null ? vote.getVoteDate() : readVoteDto.getVoteDate());

                });
    }


    public void delete(Long id)
    {
        if (id == null)
        {
            throw new AppException("ID IS NULL");
        }

        voteRepository.deleteById(id);
    }


}








