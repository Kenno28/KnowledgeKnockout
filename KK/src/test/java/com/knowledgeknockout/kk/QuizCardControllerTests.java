package com.knowledgeknockout.kk;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.knowledgeknockout.kk.entity.QuizCard;
import com.knowledgeknockout.kk.entity.User;
import com.knowledgeknockout.kk.enums.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

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


    @Test
    public void postQuizCard() throws Exception {
        User user= new User();
        user.setUsername("Xalo");
        user.setPassword("Xalo");
        user.setEmailAddress("xalo@gmail.com");
        user.setCoins(10);
        user.setVerify(true);

        QuizCard quizCard = new QuizCard();
        quizCard.setTitle("Quiz Card");
        quizCard.setQuestion("What is your name?");
        quizCard.setAnswer("Levent");
        quizCard.setGenre(Genre.INFORMATICS);
        quizCard.setUser(user);

        String quizJson = objectMapper.writeValueAsString(quizCard);

        mockMvc.perform(post("/quizCard/")
                .with(csrf())
                .with(user("testUser").password("password")))
                .andExpect(status().isCreated())
                .andReturn();
    }
}