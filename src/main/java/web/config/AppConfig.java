package web.config;

//import hiber.model.Car;
//import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;


@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScan(value = "web")
public class AppConfig {

   @Autowired
   private Environment env;

   @Autowired
   private UserService userService;

   @Bean
   public DataSource getDataSource() {
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName(env.getProperty("db.driver"));
      dataSource.setUrl(env.getProperty("db.url"));
      dataSource.setUsername(env.getProperty("db.username"));
      dataSource.setPassword(env.getProperty("db.password"));
      return dataSource;
   }

   @Bean
   public LocalSessionFactoryBean getSessionFactory() {
      LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
      factoryBean.setDataSource(getDataSource());

      Properties props=new Properties();
      props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
      props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));

      factoryBean.setHibernateProperties(props);
      factoryBean.setAnnotatedClasses(User.class, Role.class);
      return factoryBean;
   }

   @Bean
   public HibernateTransactionManager getTransactionManager() {
      HibernateTransactionManager transactionManager = new HibernateTransactionManager();
      transactionManager.setSessionFactory(getSessionFactory().getObject());
      return transactionManager;
   }

   @PostConstruct
   public void createUsers(){

      Set<Role> roleAdmin= new HashSet<>();
      roleAdmin.add(new Role(1, "ROLE_ADMIN"));

      Set<Role> roleUser = new HashSet<>();
      roleUser.add(new Role(2, "ROLE_USER"));

      userService.createUser(new User("ADMIN", "", "","0", roleAdmin));
      userService.createUser(new User("USER",  "", "","0", roleUser));

   }

}
