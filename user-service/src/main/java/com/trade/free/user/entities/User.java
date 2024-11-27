package com.trade.free.user.entities;

import com.trade.free.user.dto.UserDto;
import com.trade.free.user.enums.Role;
import com.trade.free.user.enums.UserStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "user_customer")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Integer id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "date_of_birthday")
    private OffsetDateTime dateOfBirthday;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private UserStatus status;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "delete_at")
    private OffsetDateTime deleteAt;

    private User() {
    }

    public static User create(UserDto userDto) {
        var user = new User();

        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());
        user.setSecondName(userDto.getSecondName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        user.setDateOfBirthday(userDto.getDateOfBirthday());
        user.setCreatedAt(OffsetDateTime.now());
        user.setStatus(UserStatus.ACTIVE);

        return user;
    }

    public User markAsDelete() {
        this.deleteAt = OffsetDateTime.now();
        this.status = UserStatus.DELETED;
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return this.username;
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
}
