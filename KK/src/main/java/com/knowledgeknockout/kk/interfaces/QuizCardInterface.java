package com.knowledgeknockout.kk.interfaces;

import com.knowledgeknockout.kk.entity.QuizCard;

import java.util.List;
import java.util.Optional;

public interface QuizCardInterface {
  Optional<QuizCard> updateQuizCard(QuizCard quizCard);

  List<QuizCard> getQuizCardsGenre(String genre);
}
