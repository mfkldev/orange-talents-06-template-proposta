package br.com.zupacademy.marciosouza.proposta.config.validation;

import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValidator implements ConstraintValidator<Unique, Object> {

    @Autowired
    private EntityManager entityManager;

    private Class<?> clazz;
    private String fieldName;

    @Override
    public void initialize(Unique constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.clazz = constraintAnnotation.clazz();
        this.fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        String jpql = "SELECT c FROM " + clazz.getName() + " c WHERE c." + fieldName + " = :pCampo";
        Query query =entityManager.createQuery(jpql).setParameter("pCampo", o);
        List<?> list = query.getResultList();

        return list.isEmpty();
    }
}
