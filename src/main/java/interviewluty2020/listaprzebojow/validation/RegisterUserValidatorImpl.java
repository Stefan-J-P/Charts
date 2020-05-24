package interviewluty2020.listaprzebojow.validation;



import interviewluty2020.listaprzebojow.dto.RegisterUserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RegisterUserValidatorImpl implements ConstraintValidator<RegisterUserValidator, RegisterUserDto>
{
    private String password;
    private String confirmation;

    //@Override
    public void initialize(RegisterUserValidator constraint)
    {
        password = constraint.password();
        confirmation = constraint.confirmation();
    }

    //@Override
    public boolean isValid(RegisterUserDto registerUserDto, ConstraintValidatorContext constraintValidatorContext)
    {
        return registerUserDto != null && registerUserDto.getPassword().equals(confirmation);
    }
}
