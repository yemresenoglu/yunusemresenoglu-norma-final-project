package com.example.finalproject.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseExtendedModel {

    @Column(nullable = false, length = 11)
    private String userName;

    @Column(nullable = false)
    private String password;


    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    @Past(message = "Date of birthday must be before today.")
    private Date birthday;

    @Column(nullable = false)
    @Email
    private String email;
    @Column(nullable = false)
    private String callNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus userStatus = UserStatus.ACTIVE;

    @Enumerated(EnumType.STRING)
    private UserType userType;


}
