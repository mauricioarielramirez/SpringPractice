package ar.com.taskmanager.model.domain;

import javax.persistence.*;

/**
 * Created by ArielRamirez on 3/1/2019.
 */
@Entity
@Table(name="User", schema="springtest")
public class User {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long userId;
    @Column(name="userIdentifier")
    private String userIdentifier;
    @Column(name="userAnnotation")
    private String userAnnotation;

    public User() {};

    public User(Long userId, String userIdentifier, String userAnnotation) {
        this.userId = userId;
        this.userIdentifier = userIdentifier;
        this.userAnnotation = userAnnotation;
    };

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserIdentifier() {
        return userIdentifier;
    }

    public void setUserIdentifier(String userIdentifier) {
        this.userIdentifier = userIdentifier;
    }

    public String getUserAnnotation() {
        return userAnnotation;
    }

    public void setUserAnnotation(String userAnnotation) {
        this.userAnnotation = userAnnotation;
    }
}
