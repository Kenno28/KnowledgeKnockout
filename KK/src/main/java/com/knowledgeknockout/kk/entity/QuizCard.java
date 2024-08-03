package com.knowledgeknockout.kk.entity;

import com.knowledgeknockout.kk.enums.Genre;
import com.knowledgeknockout.kk.enums.QuizChoice;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizCard {

    @Id
    @GeneratedValue
    private long id;
    @Column
    private String title;
    @Column
    private String question;
    @Column
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @Column
    @Enumerated(EnumType.STRING)
    private QuizChoice quizChoice;
    @Column
    private String answer;

    @ElementCollection
    private List<String> answerOptions;

    @JoinColumn (name = "user_id")
    private int userId;


    public QuizCard(long id, String title, String question, Genre genre, QuizChoice quizChoice, String answer, int userId) {
        this.id = id;
        this.title = title;
        this.question = question;
        this.genre = genre;
        this.quizChoice = quizChoice;
        this.answer = answer;
        this.userId = userId;
    }
}
