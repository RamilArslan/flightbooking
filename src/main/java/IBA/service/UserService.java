package IBA.service;

import IBA.model.User;

import java.util.Optional;

import static IBA.dao.SessionDao.currentUser;
import static IBA.dao.UserDao.users;

public class UserService {
    public static UserService userService = new UserService();

    public String createUser(String name, String password) {
        Optional<User> foundUser = users.stream().filter(it -> it.getUsername().equals(name)).findFirst();
        if(foundUser.isPresent()) return "User already exists";
        User newUser = new User(name, password);
        users.add(newUser);
        return "User created!";
    }
    public String login(String username, String password) {
        Optional<User> userInDao = users.stream().filter(it -> username.equals(it.getUsername())).findFirst();
        if(!userInDao.isPresent()) return "No user found!";
        if(!userInDao.get().getPassword().equals(password)) return "Wrong password";
        currentUser = userInDao.get();
        return "Logged in!";
    }
}
