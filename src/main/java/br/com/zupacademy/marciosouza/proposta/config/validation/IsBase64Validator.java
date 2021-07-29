package br.com.zupacademy.marciosouza.proposta.config.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Base64;

public class IsBase64Validator implements ConstraintValidator<IsBase64, String> {

    @Override
    public void initialize(IsBase64 constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String o, ConstraintValidatorContext constraintValidatorContext) {
        Base64.Decoder decoder = Base64.getDecoder();

        try {
            decoder.decode(o.getBytes());
            return true;
        } catch(IllegalArgumentException exception) {
            return false;
        }
    }
}
