package web.service;

//import jm.security.example.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.model.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

//    @Autowired
//    private UserDao userDao;

    @Autowired
    private UserService userService;



    // «Пользователь» – это просто Object. В большинстве случаев он может быть
    //  приведен к классу UserDetails.
    // Для создания UserDetails используется интерфейс UserDetailsService, с единственным методом:
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

//        User myUser = userDao.findUserByName(name);
//
//        if (myUser == null) {
//            throw new UsernameNotFoundException("Unknown user: "+ myUser.getName());
//        }
//
//    UserDetails user = org.springframework.security.core.userdetails.User.builder()
//            .username(myUser.getUsername())
//            .password(myUser.getPassword())
//            .roles(myUser.getAuthorities().toString())
//            .build();
//
//        System.out.println(myUser.getName());
//        System.out.println(myUser.getPassword());

        return userService.findUserByName(name);
    }
}
