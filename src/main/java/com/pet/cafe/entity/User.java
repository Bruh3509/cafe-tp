package com.pet.cafe.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Setter

public class User implements UserDetails {
    @Id
    @Column(name = "passport_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String passportId;

    @Column(name = "email", unique = true, nullable = false)
    String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "second_name")
    String secondName;

    @Column(name = "last_name")
    String lastName;
    @Column(name = "username")
    String username;

    @Column(name = "phone_number")
    String phoneNumber;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "role", nullable = false)
//    private Role role;

    public User(String passportId, String email, String firstName, String secondName, String lastName, String phoneNumber, String username) {
        this.passportId = passportId;
        this.email = email;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.username = username;
    }

    @OneToMany(mappedBy = "user")
    Set<Booking> bookings = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority(role.name()));
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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

