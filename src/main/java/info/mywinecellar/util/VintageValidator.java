/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.util;

import java.util.Calendar;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VintageValidator implements ConstraintValidator<VintageMax, Integer> {

    @Override
    public void initialize(VintageMax constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        int current = Calendar.getInstance().get(Calendar.YEAR);
        if (value == null) {
            return true;
        }
        return value <= current;
    }
}
