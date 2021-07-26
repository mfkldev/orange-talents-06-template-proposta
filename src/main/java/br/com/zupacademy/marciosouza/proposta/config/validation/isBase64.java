package br.com.zupacademy.marciosouza.proposta.config.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = IsBase64Validator.class)
public @interface isBase64 {

    String message() default "Não está em formato Base64";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}