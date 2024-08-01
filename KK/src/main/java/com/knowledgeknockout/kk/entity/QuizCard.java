package com.knowledgeknockout.kk.entity;

import com.knowledgeknockout.kk.enums.Genre;
import com.knowledgeknockout.kk.enums.QuizChoice;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

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
    private ArrayList<String> answers=new ArrayList<>();
    @Column
    private long userId;

}
