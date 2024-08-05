package com.knowledgeknockout.kk.interfaces;

import com.knowledgeknockout.kk.Games.Hangman;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HangmanRepository  extends JpaRepository<Hangman, Integer> {
}
