package com.knowledgeknockout.kk.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.persistence.Column;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;

@Table(name = "Player")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(unique = true)
    private int id;

    @Column(unique = true)
    private String Username;

    @Email
    @Column(unique = true)
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
