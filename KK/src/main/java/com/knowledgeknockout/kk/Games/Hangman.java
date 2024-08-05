package com.knowledgeknockout.kk.Games;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "Hangman")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hangman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Integer id;

    @Column
    String question;

    @Column
    String answer;

    @Column
    int guess = 11;

    @Column
    @JoinColumn(name = "questioner_id", nullable = false)
    Integer Questioner;

    @Column
    @JoinColumn(name = "answerer_id", nullable = false)
    Integer Answerer;
}
