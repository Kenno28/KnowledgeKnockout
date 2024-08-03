package com.knowledgeknockout.kk.implementations;

import com.knowledgeknockout.kk.entity.User;
import com.knowledgeknockout.kk.interfaces.QuizCardJDBC;
import com.knowledgeknockout.kk.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuizSoloGame {
    @Autowired
    QuizCardJDBC quizCardJDBC;

    @Autowired
    QuizCardImplementation quizCard;

    @Autowired
    UserRepository userRepository;


    public boolean soloQuiz (User user, String genre,String answer){

        return false;
    }
}
