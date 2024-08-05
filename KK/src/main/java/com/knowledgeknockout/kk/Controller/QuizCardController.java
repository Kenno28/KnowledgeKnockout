package com.knowledgeknockout.kk.Controller;

import com.knowledgeknockout.kk.DTO.SoloQuizDTO;
import com.knowledgeknockout.kk.Help.QuizResult;
import com.knowledgeknockout.kk.entity.QuizCard;
import com.knowledgeknockout.kk.enums.Genre;
import com.knowledgeknockout.kk.ep.QuizCardEP;
import com.knowledgeknockout.kk.implementations.QuizSoloGame;
import com.knowledgeknockout.kk.interfaces.QuizCardJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class QuizCardController implements QuizCardEP {
    @Autowired
    QuizCardJDBC quizCardJDBC;

    @Autowired
    QuizSoloGame soloGame;

    @Override
    public ResponseEntity<?> createQuizCard(QuizCard quizCard) {
        if(quizCard.getAnswer()==null || quizCard.getAnswerOptions() ==null||quizCard.getQuestion()==null|| quizCard.getGenre()==null||quizCard.getTitle()==null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            QuizCard saved=quizCardJDBC.save(quizCard);
            if(saved!=null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(saved);
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
    public ResponseEntity<?> deleteQuizCard(Long id) {
        if(id == null || id <= 0) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            Optional<QuizCard> found = quizCardJDBC.findById(id);
            if(!found.isEmpty()) {
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            quizCardJDBC.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @Override
    public ResponseEntity<?> getQuizCard(Long id) {
        if(id == null || id <= 0) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            Optional<QuizCard> found=quizCardJDBC.findById(id);
            if(!found.isEmpty()) {
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.status(HttpStatus.OK).body(found);
        }
        catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<?> updateQuizCard(QuizCard quizCard) {
      return null;
    }

    @Override
    public ResponseEntity<?> getAllQuizCards() {
        try {
            List<QuizCard> quizCards=quizCardJDBC.findAll();
            if(quizCards!=null) {
                return ResponseEntity.status(HttpStatus.OK).body(quizCards);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<?> soloQuiz(SoloQuizDTO soloQuizDTO) {

        try {
            QuizResult result =soloGame.soloQuiz(soloQuizDTO.getUser(),soloQuizDTO.getAnswers());
            if(result!=null) {
                return ResponseEntity.status(HttpStatus.OK).body(result);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Override
    public ResponseEntity<?> soloQuizRandomQuestion(String genre) {
        try {
            Genre validGenre = Genre.valueOf(genre.toUpperCase());

            List<QuizCard> quizCards =soloGame.getRandomQuizCards(genre);
            if(quizCards!=null) {
                return ResponseEntity.status(HttpStatus.OK).body(quizCards);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid genre specified.");
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
