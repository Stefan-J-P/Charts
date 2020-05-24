package interviewluty2020.listaprzebojow.service;

import interviewluty2020.listaprzebojow.dto.CreateUserDto;
import interviewluty2020.listaprzebojow.dto.Mappers;
import interviewluty2020.listaprzebojow.dto.ReadUserDto;
import interviewluty2020.listaprzebojow.dto.ReadVoteDto;
import interviewluty2020.listaprzebojow.exception.AppException;
import interviewluty2020.listaprzebojow.model.User;
import interviewluty2020.listaprzebojow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService
{
    private final UserRepository userRepository;

    public void add(CreateUserDto createUserDto)
    {
        if (createUserDto == null)
        {
            throw new AppException("CREATE USER DTO OBJECT IS NULL");
        }
        User user = Mappers.fromCreateUserDtoToUser(createUserDto);
        userRepository.save(user);
    }

    public List<ReadUserDto> getAll()
    {
        return userRepository
                .findAll()
                .stream()
                .map(Mappers::fromUserToReadUserDto)
                .collect(Collectors.toList());
    }

    public ReadUserDto getOne(Long id)
    {
        if (id == null)
        {
            throw new AppException("ID IS NULL");
        }

        return userRepository
                .findById(id)
                .map(Mappers::fromUserToReadUserDto)
                .orElseThrow(() -> new AppException("CANNOT FIND VOTE WITH ID: " + id));
    }

    public void update(ReadUserDto readUserDto)
    {
        if (readUserDto == null)
        {
            throw new AppException("READ USER DTO IS NULL");
        }

        userRepository
                .findById(readUserDto.getId())
                .ifPresent(user -> {
                    user.setName(readUserDto.getName() == null ? user.getName() : readUserDto.getName());
                    user.setSurname(readUserDto.getSurname() == null ? user.getSurname() : readUserDto.getSurname());
                    user.setAge(readUserDto.getAge() == null ? user.getAge() : readUserDto.getAge());
                    //user.setVotes(readUserDto.getReadVoteDtos() == null ? user.getVotes() : readUserDto.getReadVoteDtos());
                    user.setVotes(user.getVotes());
                });
    }

    public void delete(Long id)
    {
        if (id == null)
        {
            throw new AppException("ID IS NULL");
        }
        userRepository.deleteById(id);
    }





}















