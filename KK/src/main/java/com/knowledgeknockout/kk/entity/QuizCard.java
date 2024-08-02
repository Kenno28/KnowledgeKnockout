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
    private Genre genre;
    @Column
    private QuizChoice quizChoice;
    @Column
    private String answer;

    @ElementCollection
    private List<String> answerOptions = new ArrayList<>();

    @Column
    private long userId;

}
