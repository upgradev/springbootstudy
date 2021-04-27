package br.com.upgrade.validation.constraintvalidation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.upgrade.validation.NotEmptList;


public class NotEmptyListValidator implements ConstraintValidator<NotEmptList, List> {

    @Override
    public boolean isValid(List list, ConstraintValidatorContext context) {
        
        return list != null && !list.isEmpty();
    }

    @Override
    public void initialize(NotEmptList constratintAnnotation){
        constratintAnnotation.message();
    }
    
}