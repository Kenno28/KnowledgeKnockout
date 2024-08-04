package com.knowledgeknockout.kk.implementations;

import com.knowledgeknockout.kk.Help.QuizResult;
import com.knowledgeknockout.kk.entity.QuizCard;
import com.knowledgeknockout.kk.entity.User;
import com.knowledgeknockout.kk.enums.Genre;
import com.knowledgeknockout.kk.interfaces.QuizCardJDBC;
import com.knowledgeknockout.kk.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class QuizSoloGame {
    @Autowired
    QuizCardJDBC quizCardJDBC;

    @Autowired
    QuizCardImplementation quizCard;

    @Autowired
    UserRepository userRepository;


    public QuizResult soloQuiz (Optional <User> user, Map<Long,String> answers){
        //Überprüfen ob die antworten der quizes richtig sind und counter id
        //Coins berechnen wenn winrate über 50%
        int counter=0;

        //Gucken ob antwort mit der gewollten antwort richtig ist
        for (Long key:answers.keySet()) {
            Optional<QuizCard> foundQuizCard=quizCardJDBC.findById(key);
            if(foundQuizCard.isPresent()){
                String answer=foundQuizCard.get().getAnswer();
                if(answer.equals(answers.get(key))){
                    counter++;
                }
            }
        }
        //Berechnen der gewonnen coins wenn über 50% wr gewonnen
        double wr=0;
        if(counter==0){
            return new QuizResult(user.get().getId(),wr,0,false);
        }
        if(counter>0){
            wr= ((double) counter/ answers.size()) * 100;
        }
        if(wr>50){
            return new QuizResult(user.get().getId(),wr,100,true);
        }
        else{
            return new QuizResult(user.get().getId(),wr,0,false);
        }

    }

    public List<QuizCard> getRandomQuizCards(String genre){

        try {
            Random random = new Random();

            List<QuizCard>randomQuizCards = new ArrayList<>();
            //alle quizCards getten
            List<QuizCard> foundQuiz=quizCard.getQuizCardsGenre(genre);
            if(foundQuiz==null){
                return null;
            }
            //Nur unique zahlen bekommen
            List<Integer> randomNumbers = new ArrayList<>();
            while (randomNumbers.size()<foundQuiz.size()){
                int randomNum=random.nextInt(foundQuiz.size());
                if(!randomNumbers.contains(randomNum)){
                    randomNumbers.add(randomNum);
                }
            }
            //Mit den unique zahlen random reinfolge bekommen von den quizCards
            for (int i = 0; i < randomNumbers.size(); i++) {
                QuizCard randomFound=foundQuiz.get(randomNumbers.get(i));
                randomQuizCards.add(randomFound);
            }
            return randomQuizCards;
        }
        catch (Exception e){
            e.printStackTrace();
        }
      return null;
    }
}
