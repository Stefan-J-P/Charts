package interviewluty2020.listaprzebojow.dto;

import interviewluty2020.listaprzebojow.model.Song;
import interviewluty2020.listaprzebojow.model.User;
import interviewluty2020.listaprzebojow.model.Vote;

import java.util.HashSet;

public interface Mappers
{
    // USER --------------------------------------------
    static CreateUserDto fromUserToCreateUserDto(User user)
    {
        return user == null ? null : CreateUserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .build();
    }

    static User fromCreateUserDtoToUser(CreateUserDto createUserDto)
    {
        return createUserDto == null ? null : User.builder()
                .id(createUserDto.getId())
                .name(createUserDto.getName())
                .surname(createUserDto.getSurname())
                .age(createUserDto.getAge())
                .votes(new HashSet<>())
                .build();
    }

    static ReadUserDto fromUserToReadUserDto(User user)
    {
        return user == null ? null : ReadUserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                //.votes(user.getVotes() == null ? null : )
                .build();
    }

    static User fromReadUserDtoToUser(ReadUserDto readUserDto)
    {
        return readUserDto == null ? null : User.builder()
                .id(readUserDto.getId())
                .name(readUserDto.getName())
                .surname(readUserDto.getSurname())
                .age(readUserDto.getAge())
                //.votes()
                .build();
    }

    //                          ---- REGISTER USER ----
    static RegisterUserDto fromUserDtoToRegisterUserDto(User user)
    {
        return user == null ? null : RegisterUserDto.builder()
                //.id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                //.passwordConfirmation(user.getPassword())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    static User fromRegisterUserDtoToUser(RegisterUserDto registerUserDto)
    {
        return registerUserDto == null ? null : User.builder()
                //.id(registerUserDto.getId())
                .username(registerUserDto.getUsername())
                .password(registerUserDto.getPassword())
                .email(registerUserDto.getEmail())
                .role(registerUserDto.getRole())
                .build();
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    static RegisterUserDto fromUserDtoToCreateUserDto(CreateUserDto createUserDto)
    {
        return createUserDto == null ? null : RegisterUserDto.builder()
                //.id(user.getId())
                .username(createUserDto.getUsername())
                .password(createUserDto.getPassword())
                //.passwordConfirmation(user.getPassword())
                .email(createUserDto.getEmail())
                .role(createUserDto.getRole())
                .build();
    }

    static ReadUserDto fromRegisterUserDtoToReadUserDto(RegisterUserDto registerUserDto)
    {
        return registerUserDto == null ? null : ReadUserDto.builder()
                //.id(registerUserDto.getId())
                .username(registerUserDto.getUsername())
                .password(registerUserDto.getPassword())
                .email(registerUserDto.getEmail())
                .role(registerUserDto.getRole())
                .build();
    }



    // SONG ---------------------------------------------------------------------
    static CreateSongDto fromSongToCreateSongDto(Song song)
    {
        return song == null ? null : CreateSongDto.builder()
                .id(song.getId())
                .author(song.getAuthor())
                .title(song.getTitle())
                .genre(song.getGenre())
                .build();
    }

    static Song fromCreateSongDtoToSong(CreateSongDto createSongDto)
    {
        return createSongDto == null ? null : Song.builder()
                .id(createSongDto.getId())
                .author(createSongDto.getAuthor())
                .title(createSongDto.getTitle())
                .genre(createSongDto.getGenre())
                .votes(new HashSet<>())
                .build();
    }

    static ReadSongDto fromSongToReadSongDto(Song song)
    {
        return song == null ? null : ReadSongDto.builder()
                .id(song.getId())
                .author(song.getAuthor())
                .title(song.getTitle())
                .genre(song.getGenre())
                //.votes()
                .build();
    }

    static Song fromReadSongDtoToSong(ReadSongDto readSongDto)
    {
        return readSongDto == null ? null : Song.builder()
                .id(readSongDto.getId())
                .author(readSongDto.getAuthor())
                .title(readSongDto.getTitle())
                .genre(readSongDto.getGenre())
                //.votes(readSongDto.getReadVoteDtos() == null ? null : fromReadVoteDtoToVote(readSongDto.getReadVoteDtos()))
                .build();
    }

    // VOTES -----------------------------------------------------------
    static CreateVoteDto fromVoteToCreateVoteDto(Vote vote)
    {
        return vote == null ? null : CreateVoteDto.builder()
                .id(vote.getId())
                .voteDate(vote.getVoteDate())
                .createSongDto(vote.getSong() == null ? null : fromSongToCreateSongDto(vote.getSong()))
                .createUserDto(vote.getUser() == null ? null : fromUserToCreateUserDto(vote.getUser()))
                .build();
    }

    static Vote fromCreateVoteDtoToVote(CreateVoteDto createVoteDto)
    {
        return createVoteDto == null ? null : Vote.builder()
                .id(createVoteDto.getId())
                .voteDate(createVoteDto.getVoteDate())
                .song(createVoteDto.getCreateSongDto() == null ? null : fromCreateSongDtoToSong(createVoteDto.getCreateSongDto()))
                .user(createVoteDto.getCreateUserDto() == null ? null : fromCreateUserDtoToUser(createVoteDto.getCreateUserDto()))
                .build();
    }

    static ReadVoteDto fromVoteToReadVoteDto(Vote vote)
    {
        return vote == null ? null : ReadVoteDto.builder()
                .id(vote.getId())
                .voteDate(vote.getVoteDate())
                .readSongDto(vote.getSong() == null ? null : fromSongToReadSongDto(vote.getSong()))
                .readUserDto(vote.getUser() == null ? null : fromUserToReadUserDto(vote.getUser()))
                .build();
    }

    static Vote fromReadVoteDtoToVote(ReadVoteDto readVoteDto)
    {
        return readVoteDto == null ? null : Vote.builder()
                .id(readVoteDto.getId())
                .voteDate(readVoteDto.getVoteDate())
                .song(readVoteDto.getReadSongDto() == null ? null : fromReadSongDtoToSong(readVoteDto.getReadSongDto()))
                .user(readVoteDto.getReadUserDto() == null ? null : fromReadUserDtoToUser(readVoteDto.getReadUserDto()))
                .build();
    }










}





















