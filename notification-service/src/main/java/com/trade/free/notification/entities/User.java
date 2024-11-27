package com.trade.free.notification.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user_customer")
public class User {

    @Id
    private Integer id;


    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    private User() {
    }

    private User(Integer id) {
        this.id = id;
    }

    public static User create(Integer id, String firstName, String secondName, String email) {
        var user = new User(id);

        user.setFirstName(firstName);
        user.setSecondName(secondName);
        user.setEmail(email);


        return user;
    }
}
