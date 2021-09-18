package web.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String eMail;

    @Column(name = "password")
    private String password;

//    @OneToOne (optional=false, cascade=CascadeType.ALL)
//    @JoinColumn (name="role_id")
      @ManyToMany (cascade=CascadeType.ALL, fetch=FetchType.EAGER)
      @JoinTable (name="user_role",
        joinColumns=@JoinColumn (name="user_id"),
        inverseJoinColumns=@JoinColumn(name="role_id"))
    //private Set<Role> roles;
    private Set<Role> roles = new HashSet<>();
    public User(){

    }

//    public User(String name, String lastName, String eMail, String password, String roles) {
//        this.name = name;
//        this.lastName = lastName;
//        this.eMail = eMail;
//        setRoles(roles);
//        this.password = password;
//    }

    public User(String name, String password, Set<Role> roles) {
        this.name = name;
        this.roles = roles;
        this.password = password;
    }

    public User(String name, String lastName, String eMail, String password, Set<Role> roles) {
        this.name = name;
        this.lastName = lastName;
        this.eMail = eMail;
        this.roles = roles;
        this.password = password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public Set<Role> getRole() {
        return roles;
    }

    public void setRole(Set<Role> role) {

        this.roles = role;
    }

//    public void setRoles(String roles1) {
//        this.roles = new HashSet<>();
//        //this.roles.add(new User(), roles1);
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
