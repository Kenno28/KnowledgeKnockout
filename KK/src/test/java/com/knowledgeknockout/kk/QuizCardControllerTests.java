package com.knowledgeknockout.kk;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.knowledgeknockout.kk.Help.QuizResult;
import com.knowledgeknockout.kk.entity.QuizCard;
import com.knowledgeknockout.kk.entity.User;
import com.knowledgeknockout.kk.enums.Genre;
import com.knowledgeknockout.kk.enums.QuizChoice;
import com.knowledgeknockout.kk.implementations.QuizCardImplementation;
import com.knowledgeknockout.kk.implementations.QuizSoloGame;
import com.knowledgeknockout.kk.interfaces.UserRepository;
import jakarta.validation.constraints.AssertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class QuizCardControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private QuizCardImplementation quizCardImplementation;

    @Autowired
    private QuizSoloGame quizSoloGame;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void postQuizCard() throws Exception {
        ArrayList<String> quizCards = new ArrayList<>();
        quizCards.add("Hallo");
        quizCards.add("Kehir");
        quizCards.add("ero");
        quizCards.add("levo");

        User user= new User();
        user.setUsername("Xassxo");
        user.setPassword("Xalo");
        user.setEmailAddress("xalo@gmail.com");
        user.setCoins(10);
        user.setVerify(true);

        QuizCard quizCard = new QuizCard();
        quizCard.setTitle("qeqoen");
        quizCard.setQuestion("bbbccd");
        quizCard.setAnswer("daduijbwu");
        quizCard.setGenre(Genre.INFORMATICS);
        quizCard.setUserId(user.getId());
        quizCard.setQuizChoice(QuizChoice.MULTIPLE);
        quizCard.setAnswerOptions(quizCards);

        String quizJson = objectMapper.writeValueAsString(quizCard);

        mockMvc.perform(post("/quizCard/")
                .with(csrf())
                .with(user("testUser").password("password"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(quizJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void getQuizCardGenre() throws Exception {

        List<QuizCard> foundQuizCards=quizCardImplementation.getQuizCardsGenre("INFORMATICS");
        assertNotNull(foundQuizCards);
        assertTrue(foundQuizCards.size()==3);
    }

    @Test
    public void randomQuizCard() throws Exception {
        List<QuizCard> randomQuizCards=quizSoloGame.getRandomQuizCards("INFORMATICS");
        assertNotNull(randomQuizCards);
        assertTrue(randomQuizCards.size()==10);
        System.out.println(randomQuizCards);
    }

    @Test
    public void soloQuizAllRight() throws Exception{
        Optional <User> user= userRepository.findById(0);

        Map<Long,String> soloQuizCards=new HashMap<>();
        soloQuizCards.put(52L,"Levent");
        soloQuizCards.put(102L,"Leventdaw");
        soloQuizCards.put(152L,"Leventdawc");
        soloQuizCards.put(202L,"Leventdaddswc");
        soloQuizCards.put(252L,"Le");
        soloQuizCards.put(302L,"Led");
        soloQuizCards.put(352L,"Ledv");
        soloQuizCards.put(402L,"Ledvd");
        soloQuizCards.put(452L,"wrf");
        soloQuizCards.put(502L,"daduijbwu");


        QuizResult result =quizSoloGame.soloQuiz(user,soloQuizCards);
        assertNotNull(result);
        assertTrue(result.getWinRate()==100);
    }

    @Test
    public void soloQuizSixOfTen() throws Exception{
        Optional <User> user= userRepository.findById(0);

        Map<Long,String> soloQuizCards=new HashMap<>();
        soloQuizCards.put(52L,"Levent");
        soloQuizCards.put(102L,"Leventdaw");
        soloQuizCards.put(152L,"Leventdawc");
        soloQuizCards.put(202L,"Leventdaddswc");
        soloQuizCards.put(252L,"Le");
        soloQuizCards.put(302L,"Led");
        soloQuizCards.put(352L,"dabjdaid");
        soloQuizCards.put(402L,"adnaidji");
        soloQuizCards.put(452L,"gbjbda");
        soloQuizCards.put(502L,"dadanb");


        QuizResult result =quizSoloGame.soloQuiz(user,soloQuizCards);
        assertNotNull(result);
        assertTrue(result.getWinRate()==60);
    }

    @Test
    public void soloQuizNotWon() throws Exception{
        Optional <User> user= userRepository.findById(0);

        Map<Long,String> soloQuizCards=new HashMap<>();
        soloQuizCards.put(52L,"Levent");
        soloQuizCards.put(102L,"Leventdaw");
        soloQuizCards.put(152L,"adkandiadn");
        soloQuizCards.put(202L,"bahdba");
        soloQuizCards.put(252L,"Ldsddsdde");
        soloQuizCards.put(302L,"Led");
        soloQuizCards.put(352L,"dabjdaid");
        soloQuizCards.put(402L,"adnaidji");
        soloQuizCards.put(452L,"gbjbda");
        soloQuizCards.put(502L,"dadanb");


        QuizResult result =quizSoloGame.soloQuiz(user,soloQuizCards);
        System.out.println(result+"-------------------------");
        assertNotNull(result);
        assertFalse(result.isWL());

    }
}