package com.knowledgeknockout.kk.ep;

import com.knowledgeknockout.kk.entity.QuizCard;
import org.springframework.http.ResponseEntity;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
;

@RequestMapping("/quizCard")
public interface QuizCardEP {

    ResponseEntity<?> createCustomer(@RequestBody QuizCard quizCard);

    ResponseEntity<?> deleteCustomer(@PathVariable Long id);

    ResponseEntity <?> getQuizCard(@PathVariable Long id);

    ResponseEntity<?> updateQuizCard(@RequestBody QuizCard quizCard);

    ResponseEntity<?> getAllQuizCards();

}
