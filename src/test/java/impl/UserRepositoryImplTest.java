package impl;

import api.Logger;
import api.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryImplTest {

    private UserRepository userRepository;
    private User user;

    @BeforeEach
    public void setUP() {
        Logger logger = new LoggerImpl();
        logger.setName("LoggerUserReposytory");
        userRepository = new UserRepositoryImpl(logger);
        user = new User("login", "firstname", "surname");
    }

    @Test
    void should_create_one_user() {
        //given
        //when
        userRepository.create(user);
        //then
        assertAll(
                () -> assertEquals(1, userRepository.findAll().size()),
                () -> assertTrue(userRepository.exists(user.getLogin()))
        );
    }

    @Test
    void delete_should_work() {
        //given
        userRepository.create(user);
        //when
        userRepository.delete("login");
        //then
        assertFalse(userRepository.exists(user.getLogin()));
    }

    @Test
    public void delete_should_change_nothing_if_not_exists() {
        //given
        User user2 = new User("login2", "firstname2", "surname2");
        userRepository.create(user);
        //when
        userRepository.delete("login2");
        //then
        assertEquals(1, userRepository.findAll().size());
    }

    @Test
    public void should_throw_when_trying_to_create_user_with_duplicate_login() {
        //given
        User user2 = new User("login", "firstname2", "surname2");
        //when
        userRepository.create(user);
        //then
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> userRepository.create(user2));
    }

    @Test
    public void should_find_created_user_with_proper_all_fields() {
        //given
        //when
        userRepository.create(user);
        //then
        String expected = "f. surname";
        assertEquals(expected, userRepository.findByLogin("login").toString());
    }

    @Test
    public void findAll_should_return_exactly_created_users() {
        //given
        //when
        userRepository.create(user);
        //then
        ArrayList<User> expected = new ArrayList<>();
        expected.add(user);
        assertEquals(expected, userRepository.findAll());
    }

    @Test
    public void update_should_change_fields_in_existing_User() {
        //given
        //when
        userRepository.create(user);
        //then
        String expected = "f. marciniak";
        assertEquals(expected, userRepository.update(new User("login", "firstname", "marciniak")).toString());
    }

    @Test
    public void update_should_create_User_if_not_exists() {
        //given
        //when
        userRepository.update(user);
        //then
        assertAll(
                () -> assertEquals(1, userRepository.findAll().size()),
                () -> assertTrue(userRepository.exists(user.getLogin()))
        );
    }
}