package interviewluty2020.listaprzebojow.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RegisterUserValidatorImpl.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)

public @interface RegisterUserValidator
{
    String message() default "Passowrd and Password Confirmation are not indentical";
    String password() default ".*";
    String confirmation() default ".*";

    Class<?>[] groups() default {};
    Class<? extends Payload> [] payload() default {};

}
