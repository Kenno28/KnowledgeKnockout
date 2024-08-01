package com.knowledgeknockout.kk.entity;

import jakarta.persistence.Id;
import lombok.Data;
import jakarta.persistence.Column;

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
