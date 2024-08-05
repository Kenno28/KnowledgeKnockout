package com.knowledgeknockout.kk.ep;

import com.knowledgeknockout.kk.DTO.SoloQuizDTO;
import com.knowledgeknockout.kk.entity.QuizCard;
import org.springframework.http.ResponseEntity;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
;

@RequestMapping("/quizCard")
public interface QuizCardEP {

    @PostMapping("/")
    ResponseEntity<?> createQuizCard(@RequestBody QuizCard quizCard);

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteQuizCard(@PathVariable Long id);

    @GetMapping("/")
    ResponseEntity <?> getQuizCard(@PathVariable Long id);

    @PutMapping("/")
    ResponseEntity<?> updateQuizCard(@RequestBody QuizCard quizCard);

    @GetMapping("/all")
    ResponseEntity<?> getAllQuizCards();

    @PutMapping("/soloQuiz")
    ResponseEntity<?> soloQuiz(@RequestBody SoloQuizDTO soloQuizDTO);

    @GetMapping("/randomQuestion/{genre}")
    ResponseEntity<?> soloQuizRandomQuestion(@PathVariable("genre") String genre);
}
