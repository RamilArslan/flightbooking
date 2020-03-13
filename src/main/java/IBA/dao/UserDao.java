package IBA.dao;

import IBA.model.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements Serializable {
    public static List<User> users = new ArrayList<User>();
}
