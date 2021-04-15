package impl;

import api.Logger;
import api.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepositoryImpl implements UserRepository {
    private Map<String, User> map;
    private Logger logger;

    public UserRepositoryImpl(Logger logger) {
        map = new HashMap<>();
        this.logger = logger;
        logger.log("Repository created");
    }

    @Override
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public User findByLogin(String login) {
        logger.log("Searching for users " + login + ".");
        return map.get(login);
    }

    @Override
    public List<User> findAll() {
        logger.log("Returning the list of users.");
        return new ArrayList<>(map.values());
    }

    @Override
    public User create(User user) {
        String login = user.getLogin();
        if (map.containsKey(login)) {
            logger.log("The user already exists in the base " + login + ", throw exception.");
            throw new IllegalArgumentException("Duplicate");
        }
        map.put(login, user);
        logger.log("Adding a user " + login + " to the database.");
        return map.get(login);
    }

    @Override
    public User update(User user) {
        map.put(user.getLogin(), user);
        logger.log("Modification user " + user.getLogin() + ".");
        return map.get(user.getLogin());
    }

    @Override
    public void delete(String login) {
        logger.log("Remove user " + login + ".");
        map.remove(login);
    }

    @Override
    public boolean exists(String login) {
        logger.log("Checking if " + login + " exists.");
        return map.containsKey(login);
    }

    @Override
    public void deleteAll() {
        logger.log("Delete all users");
        map.clear();
    }


}
