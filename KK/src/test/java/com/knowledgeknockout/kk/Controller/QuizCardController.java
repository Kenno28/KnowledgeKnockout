package com.knowledgeknockout.kk.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knowledgeknockout.kk.DTO.SoloQuizDTO;
import com.knowledgeknockout.kk.entity.QuizCard;
import com.knowledgeknockout.kk.entity.User;
import com.knowledgeknockout.kk.enums.Genre;
import com.knowledgeknockout.kk.enums.QuizChoice;
import com.knowledgeknockout.kk.implementations.QuizCardImplementation;
import com.knowledgeknockout.kk.implementations.QuizSoloGame;
import com.knowledgeknockout.kk.interfaces.QuizCardJDBC;
import com.knowledgeknockout.kk.interfaces.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class QuizCardController {


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
    @Autowired
    private QuizCardJDBC quizCardJDBC;

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
    public void getRandomQuizzes() throws Exception {
        mockMvc.perform(get("/quizCard/randomQuestion/INFORMATICS")
                        .with(csrf())
                        .with(user("testUser").password("password")))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getRandomQuizzesNotFound() throws Exception {
        mockMvc.perform(get("/quizCard/randomQuestion/HISTORY")
                        .with(csrf())
                        .with(user("testUser").password("password")))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void getRandomQuizzesBadReq() throws Exception {
        mockMvc.perform(get("/quizCard/randomQuestion/HALLO")
                        .with(csrf())
                        .with(user("testUser").password("password")))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void soloQuizController() throws Exception{
        Optional<User> user= userRepository.findById(1);
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

        SoloQuizDTO soloQuizDTO= new SoloQuizDTO(user,soloQuizCards);
        String quizJson = objectMapper.writeValueAsString(soloQuizDTO);

        mockMvc.perform(put("/quizCard/soloQuiz")
                        .with(csrf())
                        .with(user("testUser").password("password"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(quizJson))
                .andExpect(status().isOk());
    }

}
