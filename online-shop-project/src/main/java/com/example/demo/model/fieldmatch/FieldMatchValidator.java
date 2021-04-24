package com.example.demo.model.fieldmatch;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object>{

	 private String firstFieldName;
	  private String secondFieldName;
	  private String message;

	  @Override
	  public void initialize(FieldMatch constraintAnnotation) {
	    this.firstFieldName = constraintAnnotation.first();
	    this.secondFieldName = constraintAnnotation.second();
	    this.message = constraintAnnotation.message();
	  }

	  @Override
	  public boolean isValid(Object value, ConstraintValidatorContext context) {
	    boolean valid;

	    BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(value);
	    final Object firstObj = beanWrapper.getPropertyValue(firstFieldName);
	    final Object secondObj = beanWrapper.getPropertyValue(secondFieldName);

	    valid = firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);

	    if (!valid) {
	      context.
	          buildConstraintViolationWithTemplate(message)
	            .addPropertyNode(firstFieldName)
	            .addConstraintViolation().
	          buildConstraintViolationWithTemplate(message).
	            addPropertyNode(secondFieldName).
	            addConstraintViolation().
	          disableDefaultConstraintViolation();


	    }

	    return valid;
	  }
	}
