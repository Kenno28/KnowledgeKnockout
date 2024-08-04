package com.knowledgeknockout.kk.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;

@Table(name = "Player")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Integer id;

    @Column(unique = true)
    private String Username;

    @Column(unique = true)
    @Email(message = "Email should be valid")
    private String EmailAddress;

    @Column
    private String Password;

    @Column
    private Boolean Verify;

    @Column
    private int Coins;

    @Column
    private Long profilePictureOid;


    public User(String username, String emailAddress, String password) {
        Username = username;
        EmailAddress = emailAddress;
        Password = password;
        Coins = 0;
        Verify = false;
        profilePictureOid = 0L;
    }

}
