package info.mywinecellar.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;

import javax.validation.ConstraintValidatorContext;

import static org.junit.jupiter.api.Assertions.*;

class VintageValidatorTest {

    VintageValidator validator;
    ConstraintValidatorContext context = Mockito.mock(ConstraintValidatorContext.class);

    @BeforeEach
    void init(){
        validator = new VintageValidator();
    }

    @Test
    void isValid_valueIsNull_returnsTrue(){
        assertTrue(validator.isValid(null, context));
    }

    @Test
    void isValid_valueIs1990_returnsTrue(){
        assertTrue(validator.isValid(1990, context));
    }

    @Test
    void isValid_valueIs2020_returnsTrue(){
        assertTrue(validator.isValid(2020, context));
    }

    @Test
    void isValid_valueIs2021_returnsFalse(){
        assertFalse(validator.isValid(2021, context));
    }
}