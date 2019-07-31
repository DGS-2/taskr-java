package micf.taskr.validation.user;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import micf.taskr.domain.user.UserDto;

@Component
public class UserValidator implements Validator {
    
    @Override
    public boolean supports(final Class<?> aClass) {
        return UserDto.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(final Object obj, final Errors errors) {
        
        UserDto user = (UserDto) obj;

        if(user.getPassword().length() < 10) errors.rejectValue("password", "Length", "Password must be at least 11 characters");
        if(!user.getPassword().equals(user.getMatchingPassword())) errors.rejectValue("confirmPassword", "Match", "Passwords do not match");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "message.firstName", "Firstname is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "message.lastName", "LastName is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "message.password", "Password is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "message.email", "Email is required.");

        
    }
}