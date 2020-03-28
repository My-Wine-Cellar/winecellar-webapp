/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.unit;

import info.mywinecellar.WineWebappApplication;
import info.mywinecellar.dto.UserRegisterDto;
import info.mywinecellar.dto.UserResetDto;
import info.mywinecellar.exception.EmailException;
import info.mywinecellar.exception.PasswordException;
import info.mywinecellar.exception.UsernameException;
import info.mywinecellar.model.User;
import info.mywinecellar.service.AuthorityRepository;
import info.mywinecellar.service.UserRepository;
import info.mywinecellar.service.UserServiceImpl;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = WineWebappApplication.class)
class UserServiceUnitTest {

    @Inject
    UserServiceImpl userServiceImpl;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    AuthorityRepository authorityRepository;

    @MockBean
    UserRepository userRepository;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void registerNewUserWithNullNameAndGetAnException() throws Exception {
        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setFirstName(null);
        userRegisterDto.setLastName("Cellar");
        userRegisterDto.setPassword("123456ABab$$");
        userRegisterDto.setMatchingPassword("123456ABab$$");
        userRegisterDto.setEmail("mywinecellarinfo@gmail.com");
        userRegisterDto.setUserName("mywinecellaruser");

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            userServiceImpl.registerNewUserAccount(userRegisterDto);
        });
    }

    @Test
    void registerNewUserWithEmptyNameAndGetAnException() throws Exception {
        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setFirstName("");
        userRegisterDto.setLastName("Cellar");
        userRegisterDto.setPassword("123456ABab$$");
        userRegisterDto.setMatchingPassword("123456ABab$$");
        userRegisterDto.setEmail("mywinecellarinfo@gmail.com");
        userRegisterDto.setUserName("mywinecellaruser");

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            userServiceImpl.registerNewUserAccount(userRegisterDto);
        });
    }

    @Test
    void registerNewUserWithNullLastNameAndGetAnException() throws Exception {
        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setFirstName("Wine");
        userRegisterDto.setLastName(null);
        userRegisterDto.setPassword("123456ABab$$");
        userRegisterDto.setMatchingPassword("123456ABab$$");
        userRegisterDto.setEmail("mywinecellarinfo@gmail.com");
        userRegisterDto.setUserName("mywinecellaruser");

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            userServiceImpl.registerNewUserAccount(userRegisterDto);
        });
    }

    @Test
    void registerNewUserWithEmptyLastNameAndGetAnException() throws Exception {
        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setFirstName("Wine");
        userRegisterDto.setLastName("");
        userRegisterDto.setPassword("123456ABab$$");
        userRegisterDto.setMatchingPassword("123456ABab$$");
        userRegisterDto.setEmail("mywinecellarinfo@gmail.com");
        userRegisterDto.setUserName("mywinecellaruser");

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            userServiceImpl.registerNewUserAccount(userRegisterDto);
        });
    }


    @Test
    void registerNewUserWithPasswordLesserThan8CharsAndGetAnException() throws Exception {
        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setFirstName("Wine");
        userRegisterDto.setLastName("Cellar");
        userRegisterDto.setPassword("16ABa$");
        userRegisterDto.setMatchingPassword("16ABa$");
        userRegisterDto.setEmail("mywinecellarinfo@gmail.com");
        userRegisterDto.setUserName("winecellar");

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            userServiceImpl.registerNewUserAccount(userRegisterDto);
        });
    }

    @Test
    void registerNewUserWithPasswordNotUsingDigitAndGetAnException() throws Exception {
        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setFirstName("Wine");
        userRegisterDto.setLastName("Cellar");
        userRegisterDto.setPassword("AAAAAAABab$$");
        userRegisterDto.setMatchingPassword("AAAAAAABab$$");
        userRegisterDto.setEmail("mywinecellarinfo@gmail.com");
        userRegisterDto.setUserName("winecellar");

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            userServiceImpl.registerNewUserAccount(userRegisterDto);
        });
    }

    @Test
    void registerNewUserWithPasswordNotUsingSpecialCharAndGetAnException() throws Exception {
        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setFirstName("Wine");
        userRegisterDto.setLastName("Cellar");
        userRegisterDto.setPassword("123456ABababc");
        userRegisterDto.setMatchingPassword("123456ABab$$");
        userRegisterDto.setEmail("mywinecellarinfo@gmail.com");
        userRegisterDto.setUserName("winecellar");

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            userServiceImpl.registerNewUserAccount(userRegisterDto);
        });
    }

    @Test
    void registerNewUserWithPasswordNotUsingUppercaseCharAndGetAnException() throws Exception {
        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setFirstName("Wine");
        userRegisterDto.setLastName("Cellar");
        userRegisterDto.setPassword("123456abab$$");
        userRegisterDto.setMatchingPassword("123456abab$$");
        userRegisterDto.setEmail("mywinecellarinfo@gmail.com");
        userRegisterDto.setUserName("winecellar");

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            userServiceImpl.registerNewUserAccount(userRegisterDto);
        });
    }

    @Test
    void registerNewUserWithPasswordNotUsingLowercaseCharAndGetAnException() throws Exception {
        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setFirstName("Wine");
        userRegisterDto.setLastName("Cellar");
        userRegisterDto.setPassword("123456ABAB$$");
        userRegisterDto.setMatchingPassword("123456ABAB$$");
        userRegisterDto.setEmail("mywinecellarinfo@gmail.com");
        userRegisterDto.setUserName("winecellar");

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            userServiceImpl.registerNewUserAccount(userRegisterDto);
        });
    }

    @Test
    void registerNewUserWithPasswordUsingWhiteSpaceAndGetAnException() throws Exception {
        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setFirstName("Wine");
        userRegisterDto.setLastName("Cellar");
        userRegisterDto.setPassword("123456AB ab$$");
        userRegisterDto.setMatchingPassword("123456AB ab$$");
        userRegisterDto.setEmail("mywinecellarinfo@gmail.com");
        userRegisterDto.setUserName("winecellar");

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            userServiceImpl.registerNewUserAccount(userRegisterDto);
        });
    }

    @Test
    void registerNewUserWithDifferentPasswordAndGetAnException() throws Exception {
        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setFirstName("Wine");
        userRegisterDto.setLastName("Cellar");
        userRegisterDto.setPassword("123456ABab$$");
        userRegisterDto.setMatchingPassword("123456ABab$1");
        userRegisterDto.setEmail("mywinecellarinfo@gmail.com");
        userRegisterDto.setUserName("winecellar");

        Assertions.assertThrows(PasswordException.class, () -> {
            userServiceImpl.registerNewUserAccount(userRegisterDto);
        });
    }

    @Test
    void registerNewUserWithEmailButNoAtCharAndGetAnException() throws Exception {
        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setFirstName("Wine");
        userRegisterDto.setLastName("Cellar");
        userRegisterDto.setPassword("123456ABab$$");
        userRegisterDto.setMatchingPassword("123456ABab$$");
        userRegisterDto.setEmail("mywinecellarinfogmail.com");
        userRegisterDto.setUserName("winecellar");

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            userServiceImpl.registerNewUserAccount(userRegisterDto);
        });
    }

    @Test
    void registerNewUserWithEmailWithoutDomainAndGetAnException() throws Exception {
        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setFirstName("Wine");
        userRegisterDto.setLastName("Cellar");
        userRegisterDto.setPassword("123456ABab$$");
        userRegisterDto.setMatchingPassword("123456ABab$$");
        userRegisterDto.setEmail("mywinecellarinfo@gmail");
        userRegisterDto.setUserName("winecellar");

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            userServiceImpl.registerNewUserAccount(userRegisterDto);
        });
    }

    @Test
    void registerNewUserWithSuccess() throws Exception {
        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setFirstName("Wine");
        userRegisterDto.setLastName("Cellar");
        userRegisterDto.setPassword("123456ABab$$");
        userRegisterDto.setMatchingPassword("123456ABab$$");
        userRegisterDto.setEmail("mywinecellarinfo@gmail.com");
        userRegisterDto.setUserName("winecellar");

        Assertions.assertDoesNotThrow(() -> {
            userServiceImpl.registerNewUserAccount(userRegisterDto);
        });
    }

    @Test
    void registerNewUserWithAlreadySubscribedEmailAndGetAnException() throws Exception {
        User user = new User();
        user.setFirstName("Wine");
        user.setLastName("Cellar");
        user.setEmail("mywinecellarinfo@gmail.com");
        user.setPassword("123456ABab$$");

        when(userRepository.findUserByEmail("mywinecellarinfo@gmail.com"))
                .thenReturn(user);

        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setFirstName("Wine");
        userRegisterDto.setLastName("Cellar");
        userRegisterDto.setPassword("123456ABab$$");
        userRegisterDto.setMatchingPassword("123456ABab$$");
        userRegisterDto.setEmail("mywinecellarinfo@gmail.com");
        userRegisterDto.setUserName("winecellar");

        Assertions.assertThrows(EmailException.class, () -> {
            userServiceImpl.registerNewUserAccount(userRegisterDto);
        });
    }

    @Test
    void registerNewUserWithAlreadyUsedUsernameAndGetAnException() throws Exception {
        User user = new User();
        user.setFirstName("Wine");
        user.setLastName("Cellar");
        user.setUsername("winecellar");
        user.setEmail("mywinecellarinfo@gmail.com");
        user.setPassword("123456ABab$$");

        when(userRepository.findUserByUsername("winecellar"))
                .thenReturn(user);

        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setFirstName("Wine");
        userRegisterDto.setLastName("Cellar");
        userRegisterDto.setPassword("123456ABab$$");
        userRegisterDto.setMatchingPassword("123456ABab$$");
        userRegisterDto.setEmail("mywinecellarinfo@gmail.com");
        userRegisterDto.setUserName("winecellar");

        Assertions.assertThrows(UsernameException.class, () -> {
            userServiceImpl.registerNewUserAccount(userRegisterDto);
        });
    }

    @Test
    void passwordReset() {
        User user = new User();
        user.setFirstName("Wine");
        user.setLastName("Cellar");
        user.setUsername("winecellar");
        user.setEmail("mywinecellarinfo@gmail.com");
        user.setPassword("123456ABab$$");

        when(userRepository.findUserByUsername("winecellar"))
                .thenReturn(user);

        UserResetDto userDto = new UserResetDto();
        userDto.setUserName("winecellar");
        userDto.setPassword("123456ABab!!");

        assertEquals(user.getUsername(), userDto.getUserName());
        assertNotEquals(user.getPassword(), userDto.getPassword());
        userServiceImpl.resetPassword(userDto);
    }

}
