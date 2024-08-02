package com.knowledgeknockout.kk.Controller;

import com.knowledgeknockout.kk.entity.QuizCard;
import com.knowledgeknockout.kk.ep.QuizCardEP;
import com.knowledgeknockout.kk.interfaces.QuizCardJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuizCardController implements QuizCardEP {
    @Autowired
    QuizCardJDBC quizCardJDBC;

    @Override
    public ResponseEntity<?> createCustomer(QuizCard quizCard) {
        if(quizCard == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            QuizCard saved=quizCardJDBC.save(quizCard);
            if(saved!=null) {
                return ResponseEntity.status(HttpStatus.OK).body(saved);
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<?> deleteCustomer(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> getQuizCard(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateQuizCard(QuizCard quizCard) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAllQuizCards() {
        return null;
    }
}
