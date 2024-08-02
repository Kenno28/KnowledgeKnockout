package com.knowledgeknockout.kk;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.knowledgeknockout.kk.entity.QuizCard;
import com.knowledgeknockout.kk.enums.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QuizCardControllerTests {

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    public void postQuizCard() {
        QuizCard quizCard = new QuizCard();
        quizCard.setTitle("Quiz Card");
        quizCard.setQuestion("What is your name?");
        quizCard.setAnswer("Levent");
        quizCard.setGenre(Genre.INFORMATICS);

    }
}