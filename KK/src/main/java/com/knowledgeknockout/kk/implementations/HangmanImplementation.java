package com.knowledgeknockout.kk.implementations;

import com.knowledgeknockout.kk.DTO.HangmanDTO;
import com.knowledgeknockout.kk.Games.Hangman;
import com.knowledgeknockout.kk.entity.User;
import com.knowledgeknockout.kk.interfaces.HangmanRepository;
import com.knowledgeknockout.kk.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HangmanImplementation {

    @Autowired
    private HangmanRepository hr;

    @Autowired
    private UserRepository userRepository;

    public boolean createHangman(Hangman dto) {
        if(dto.getQuestion() == null || dto.getAnswer() == null) {
            return false;
        }

        hr.save(dto);
        return true;
    }

    public Hangman findGame(Integer ID){
        if(!hr.existsById(ID)){
            return null;
        }

        return hr.findById(ID).get();
    }

    public HangmanDTO isRightAnswer(HangmanDTO Answer, Integer ID){
        if(!hr.existsById(ID)){
            return null;
        }
        Hangman hm = findGame(ID);

        if (!hm.getQuestion().contains(Answer.getLetter())) {
            Answer.setTries(Answer.getTries() - 1);
        }

        hm.setGuess(Answer.getTries());
        hr.save(hm);
        return Answer;
    }

    public boolean gameDone(Integer ID, User User){
        if(!hr.existsById(ID) || !userRepository.existsById(ID)){
            return false;
        }

        try {
            hr.deleteById(ID);
            User user = userRepository.findById(User.getId()).get();
            user.setCoins(user.getCoins() + 100);
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
