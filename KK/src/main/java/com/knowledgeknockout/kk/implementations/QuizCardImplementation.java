package com.knowledgeknockout.kk.implementations;

import com.knowledgeknockout.kk.entity.QuizCard;
import com.knowledgeknockout.kk.enums.Genre;
import com.knowledgeknockout.kk.enums.QuizChoice;
import com.knowledgeknockout.kk.interfaces.QuizCardInterface;
import com.knowledgeknockout.kk.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuizCardImplementation implements QuizCardInterface {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRepository userRepository;

    @Override
    public QuizCard updateQuizCard(QuizCard quizCard) {
        return null;
    }
}
