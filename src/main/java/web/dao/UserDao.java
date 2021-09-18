package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    List<User> showAllUsers();

     User showUser(Integer id);

     void createUser(User user);

     void updateUser(User user);

     void deleteUser(Integer id);

     User findUserByName(String name);
}
