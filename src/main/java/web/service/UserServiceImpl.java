package web.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDaoImpl;
import web.model.User;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    private final UserDaoImpl userDao;

    public UserServiceImpl(UserDaoImpl userDao) {
        this.userDao = userDao;
    }


    @Override
    public List<User> showAllUsers() {
        return userDao.showAllUsers();
    }


    @Override
    public User showUser(Integer id){
        return userDao.showUser(id);
    }


    @Override
    public void createUser(User user) {
        userDao.createUser(user);
    }


    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }


    @Override
    public void deleteUser(Integer id) {
        userDao.deleteUser(id);
    }

    @Override
    public User findUserByName(String name) {
        return userDao.findUserByName(name);
    }

    @Override
    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

}
