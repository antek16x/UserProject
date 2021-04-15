package api;

import impl.User;

import java.util.List;

public interface UserRepository {

    void setLogger(Logger logger);

    User findByLogin(String login);

    List<User> findAll();

    User create(User user);

    User update(User user);

    void delete(String login);

    boolean exists(String login);

    void deleteAll();

}

