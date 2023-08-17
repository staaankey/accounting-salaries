package staaankey.group.accountingsalaries.registration.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Entity(name = "users")
public class User {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer userId;
    private String login;
    private String password;

    public Integer getId() {
        return userId;
    }

    public User() {
    }

    public User(int userId, String login, String password) {
        this.userId = userId;
        this.login = login;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
