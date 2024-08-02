package com.knowledgeknockout.kk.interfaces;

import com.knowledgeknockout.kk.entity.QuizCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizCardJDBC extends JpaRepository<QuizCard, Long> {
}
