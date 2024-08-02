package com.knowledgeknockout.kk.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.Column;

@Table(name = "Player")
@Entity
@Data
public class User {

    @Id
    @Column(unique = true)
    private int id;

    @Column(unique = true)
    private String Username;

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
}
