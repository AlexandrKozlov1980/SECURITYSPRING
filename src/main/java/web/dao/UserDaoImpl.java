package web.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> showAllUsers() {
        return sessionFactory.getCurrentSession().createQuery("from User").getResultList();
    }

    @Override
    public User showUser(Integer id){
        return (User) sessionFactory.getCurrentSession().createQuery("from User e where e.id = :id")
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public void createUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public void deleteUser(Integer id){
        sessionFactory.getCurrentSession().delete(showUser(id));
    }

    @Override
    public User findUserByName(String name) {
        return (User) sessionFactory.getCurrentSession().createQuery("from User e where e.name = :name")
                .setParameter("name", name).getSingleResult();
    }
}
