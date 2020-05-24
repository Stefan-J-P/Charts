package interviewluty2020.listaprzebojow.validation.spring;

import interviewluty2020.listaprzebojow.dto.RegisterUserDto;
import interviewluty2020.listaprzebojow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class RegisterUserValidator implements Validator
{
    private final UserRepository userRepository;

    @Override
    public boolean supports(Class<?> aClass)
    {
        return RegisterUserDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors)
    {
        RegisterUserDto registerUserDto = (RegisterUserDto)o;

        if (registerUserDto.getUsername() == null || !registerUserDto.getUsername().matches("[a-z]+")) {
            errors.rejectValue("username", "username is not correct");
        }

        if (registerUserDto.getEmail() == null || !EmailValidator.getInstance().isValid(registerUserDto.getEmail())) {
            errors.rejectValue("email", "email is not correct");
        }

        if (userRepository.findByUsername(registerUserDto.getUsername()).isPresent()) {
            errors.rejectValue("username", "username already exists");
        }

        if (userRepository.findByEmail(registerUserDto.getEmail()).isPresent()) {
            errors.rejectValue("email", "username already exists");
        }

        if (!Objects.equals(registerUserDto.getPassword(), registerUserDto.getPasswordConfirmation())) {
            errors.rejectValue("password", "password is not correct");
        }

        if (registerUserDto.getRole() == null) {
            errors.rejectValue("role", "role is not correct");
        }
    }
}
