package org.fredohm.springbootintranet.validation;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, Object> {

    private String passwordField;
    private String confirmedPasswordField;
    private String message;

    @Override
    public void initialize(final PasswordMatch constraintAnnotation) {
        passwordField = constraintAnnotation.password();
        confirmedPasswordField = constraintAnnotation.confirmedPassword();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        boolean valid = true;

        try {
            final Object passwordObject = new BeanWrapperImpl(value).getPropertyValue(passwordField);
            final Object confirmedPasswordObject = new BeanWrapperImpl(value).getPropertyValue(confirmedPasswordField);

            valid = passwordObject == null && confirmedPasswordObject == null
                    || passwordObject != null && passwordObject.equals(confirmedPasswordObject);
        } catch (final Exception ignore) {}

        if (!valid) {
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(passwordField)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return valid;
    }
}
