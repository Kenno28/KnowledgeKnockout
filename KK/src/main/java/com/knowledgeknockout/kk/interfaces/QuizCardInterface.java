package com.knowledgeknockout.kk.interfaces;

import com.knowledgeknockout.kk.entity.QuizCard;

public interface QuizCardInterface {
  QuizCard createQuizCard(QuizCard quizCard);
  QuizCard getQuizCard(int id);
  QuizCard deleteQuizCard(int id);
  QuizCard updateQuizCard(QuizCard quizCard);
  QuizCard getAllQuizCards();
}
