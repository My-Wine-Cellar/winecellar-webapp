/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.unit;

import info.mywinecellar.WineWebappApplication;
import info.mywinecellar.security.exception.EmailException;
import info.mywinecellar.security.exception.PasswordException;
import info.mywinecellar.security.exception.UsernameException;
import info.mywinecellar.security.model.User;
import info.mywinecellar.security.model.UserDto;
import info.mywinecellar.security.service.AuthorityRepository;
import info.mywinecellar.security.service.UserRepository;
import info.mywinecellar.security.service.UserServiceImpl;

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
        UserDto userDto = new UserDto();
        userDto.setFirstName(null);
        userDto.setLastName("Cellar");
        userDto.setPassword("123456ABab$$");
        userDto.setMatchingPassword("123456ABab$$");
        userDto.setEmail("mywinecellarinfo@gmail.com");
        userDto.setUserName("mywinecellaruser");

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            userServiceImpl.registerNewUserAccount(userDto);
        });
    }

    @Test
    void registerNewUserWithNameLongerThan30CharsAndGetAnException() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setFirstName("WinecellarWineCellarWineCellarWine");
        userDto.setLastName("Cellar");
        userDto.setPassword("123456ABab$$");
        userDto.setMatchingPassword("123456ABab$$");
        userDto.setEmail("mywinecellarinfo@gmail.com");
        userDto.setUserName("mywinecellaruser");

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            userServiceImpl.registerNewUserAccount(userDto);
        });
    }

    @Test
    void registerNewUserWithEmptyNameAndGetAnException() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setFirstName("");
        userDto.setLastName("Cellar");
        userDto.setPassword("123456ABab$$");
        userDto.setMatchingPassword("123456ABab$$");
        userDto.setEmail("mywinecellarinfo@gmail.com");
        userDto.setUserName("mywinecellaruser");

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            userServiceImpl.registerNewUserAccount(userDto);
        });
    }

    @Test
    void registerNewUserWithNullLastNameAndGetAnException() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setFirstName("Wine");
        userDto.setLastName(null);
        userDto.setPassword("123456ABab$$");
        userDto.setMatchingPassword("123456ABab$$");
        userDto.setEmail("mywinecellarinfo@gmail.com");
        userDto.setUserName("mywinecellaruser");

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            userServiceImpl.registerNewUserAccount(userDto);
        });
    }

    @Test
    void registerNewUserWithLastNameLongerThan30CharsAndGetAnException() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setFirstName("Wine");
        userDto.setLastName("WinecellarWineCellarWineCellarWine");
        userDto.setPassword("123456ABab$$");
        userDto.setMatchingPassword("123456ABab$$");
        userDto.setEmail("mywinecellarinfo@gmail.com");
        userDto.setUserName("mywinecellaruser");

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            userServiceImpl.registerNewUserAccount(userDto);
        });
    }

    @Test
    void registerNewUserWithEmptyLastNameAndGetAnException() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setFirstName("Wine");
        userDto.setLastName("");
        userDto.setPassword("123456ABab$$");
        userDto.setMatchingPassword("123456ABab$$");
        userDto.setEmail("mywinecellarinfo@gmail.com");
        userDto.setUserName("mywinecellaruser");

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            userServiceImpl.registerNewUserAccount(userDto);
        });
    }


    @Test
    void registerNewUserWithUserNameLesserThan6CharsAndGetAnException() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setFirstName("Wine");
        userDto.setLastName("Cellar");
        userDto.setPassword("123456ABab$$");
        userDto.setMatchingPassword("123456ABab$$");
        userDto.setEmail("mywinecellarinfo@gmail.com");
        userDto.setUserName("mywin");

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            userServiceImpl.registerNewUserAccount(userDto);
        });
    }

    @Test
    void registerNewUserWithUserNameLongerThan30CharsAndGetAnException() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setFirstName("Wine");
        userDto.setLastName("Cellar");
        userDto.setPassword("123456ABab$$");
        userDto.setMatchingPassword("123456ABab$$");
        userDto.setEmail("mywinecellarinfo@gmail.com");
        userDto.setUserName("WinecellarWineCellarWineCellarWine");

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            userServiceImpl.registerNewUserAccount(userDto);
        });
    }

    @Test
    void registerNewUserWithPasswordLesserThan8CharsAndGetAnException() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setFirstName("Wine");
        userDto.setLastName("Cellar");
        userDto.setPassword("16ABa$");
        userDto.setMatchingPassword("16ABa$");
        userDto.setEmail("mywinecellarinfo@gmail.com");
        userDto.setUserName("winecellar");

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            userServiceImpl.registerNewUserAccount(userDto);
        });
    }

    @Test
    void registerNewUserWithPasswordLongerThan30CharsAndGetAnException() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setFirstName("Wine");
        userDto.setLastName("Cellar");
        userDto.setPassword("16ABa$16ABa$16ABa$16ABa$16ABa$16ABa$");
        userDto.setMatchingPassword("16ABa$16ABa$16ABa$16ABa$16ABa$16ABa$");
        userDto.setEmail("mywinecellarinfo@gmail.com");
        userDto.setUserName("winecellar");

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            userServiceImpl.registerNewUserAccount(userDto);
        });
    }

    @Test
    void registerNewUserWithPasswordNotUsingDigitAndGetAnException() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setFirstName("Wine");
        userDto.setLastName("Cellar");
        userDto.setPassword("AAAAAAABab$$");
        userDto.setMatchingPassword("AAAAAAABab$$");
        userDto.setEmail("mywinecellarinfo@gmail.com");
        userDto.setUserName("winecellar");

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            userServiceImpl.registerNewUserAccount(userDto);
        });
    }

    @Test
    void registerNewUserWithPasswordNotUsingSpecialCharAndGetAnException() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setFirstName("Wine");
        userDto.setLastName("Cellar");
        userDto.setPassword("123456ABababc");
        userDto.setMatchingPassword("123456ABab$$");
        userDto.setEmail("mywinecellarinfo@gmail.com");
        userDto.setUserName("winecellar");

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            userServiceImpl.registerNewUserAccount(userDto);
        });
    }

    @Test
    void registerNewUserWithPasswordNotUsingUppercaseCharAndGetAnException() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setFirstName("Wine");
        userDto.setLastName("Cellar");
        userDto.setPassword("123456abab$$");
        userDto.setMatchingPassword("123456abab$$");
        userDto.setEmail("mywinecellarinfo@gmail.com");
        userDto.setUserName("winecellar");

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            userServiceImpl.registerNewUserAccount(userDto);
        });
    }

    @Test
    void registerNewUserWithPasswordNotUsingLowercaseCharAndGetAnException() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setFirstName("Wine");
        userDto.setLastName("Cellar");
        userDto.setPassword("123456ABAB$$");
        userDto.setMatchingPassword("123456ABAB$$");
        userDto.setEmail("mywinecellarinfo@gmail.com");
        userDto.setUserName("winecellar");

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            userServiceImpl.registerNewUserAccount(userDto);
        });
    }

    @Test
    void registerNewUserWithPasswordUsingWhiteSpaceAndGetAnException() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setFirstName("Wine");
        userDto.setLastName("Cellar");
        userDto.setPassword("123456AB ab$$");
        userDto.setMatchingPassword("123456AB ab$$");
        userDto.setEmail("mywinecellarinfo@gmail.com");
        userDto.setUserName("winecellar");

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            userServiceImpl.registerNewUserAccount(userDto);
        });
    }

    @Test
    void registerNewUserWithDifferentPasswordAndGetAnException() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setFirstName("Wine");
        userDto.setLastName("Cellar");
        userDto.setPassword("123456ABab$$");
        userDto.setMatchingPassword("123456ABab$1");
        userDto.setEmail("mywinecellarinfo@gmail.com");
        userDto.setUserName("winecellar");

        Assertions.assertThrows(PasswordException.class, () -> {
            userServiceImpl.registerNewUserAccount(userDto);
        });
    }

    @Test
    void registerNewUserWithEmailButNoAtCharAndGetAnException() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setFirstName("Wine");
        userDto.setLastName("Cellar");
        userDto.setPassword("123456ABab$$");
        userDto.setMatchingPassword("123456ABab$$");
        userDto.setEmail("mywinecellarinfogmail.com");
        userDto.setUserName("winecellar");

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            userServiceImpl.registerNewUserAccount(userDto);
        });
    }

    @Test
    void registerNewUserWithEmailWithoutDomainAndGetAnException() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setFirstName("Wine");
        userDto.setLastName("Cellar");
        userDto.setPassword("123456ABab$$");
        userDto.setMatchingPassword("123456ABab$$");
        userDto.setEmail("mywinecellarinfo@gmail");
        userDto.setUserName("winecellar");

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            userServiceImpl.registerNewUserAccount(userDto);
        });
    }

    @Test
    void registerNewUserWithSuccess() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setFirstName("Wine");
        userDto.setLastName("Cellar");
        userDto.setPassword("123456ABab$$");
        userDto.setMatchingPassword("123456ABab$$");
        userDto.setEmail("mywinecellarinfo@gmail.com");
        userDto.setUserName("winecellar");

        Assertions.assertDoesNotThrow(() -> {
                userServiceImpl.registerNewUserAccount(userDto);
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

        UserDto userDto = new UserDto();
        userDto.setFirstName("Wine");
        userDto.setLastName("Cellar");
        userDto.setPassword("123456ABab$$");
        userDto.setMatchingPassword("123456ABab$$");
        userDto.setEmail("mywinecellarinfo@gmail.com");
        userDto.setUserName("winecellar");

        Assertions.assertThrows(EmailException.class, () -> {
            userServiceImpl.registerNewUserAccount(userDto);
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

        UserDto userDto = new UserDto();
        userDto.setFirstName("Wine");
        userDto.setLastName("Cellar");
        userDto.setPassword("123456ABab$$");
        userDto.setMatchingPassword("123456ABab$$");
        userDto.setEmail("mywinecellarinfo@gmail.com");
        userDto.setUserName("winecellar");

        Assertions.assertThrows(UsernameException.class, () -> {
            userServiceImpl.registerNewUserAccount(userDto);
        });
    }

}
