package com.knowledgeknockout.kk;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knowledgeknockout.kk.entity.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void registerTest() throws Exception {
        User user = new User("eray2356", "erayzor048123@gmail.com", "1231A23!");

        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);

        mockMvc.perform(post("/player/register")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void RegisterTestPasswordNotGoodEnough() throws Exception {
        User user = new User("eray", "erayzor046@gmail.com", "123!");

        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);

        mockMvc.perform(post("/player/register")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Password did not satisfy the requirement"));
    }

    @Test
    public void RegisterTestNotAnEmail() throws Exception {
        User user = new User("eray", "erayzor04", "1231A23!");

        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);

        mockMvc.perform(post("/player/register")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updatePlayerTest() throws Exception {
        User user = new User("eray123121233", "erayzor04623123@gmail.com", "123Acsd!sdad!");
        user.setId(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);

        mockMvc.perform(put("/player/")
                        .with(csrf())
                        .with(user("testUser").password("password"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk());
    }

    @Test
    public void updatePlayerTestFailNoID() throws Exception {
        User user = new User("eray", "erayzor046@gmail.com", "123!");
        user.setId(null);
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);

        mockMvc.perform(put("/player/")
                        .with(csrf())
                        .with(user("testUser").password("password"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updatePlayerTestFailNoUsername() throws Exception {
        User user = new User("eray", "erayzor046@gmail.com", "123!");
        user.setUsername(null);
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);

        mockMvc.perform(put("/player/")
                        .with(csrf())
                        .with(user("testUser").password("password"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deletePlayerTest() throws Exception {

        mockMvc.perform(delete("/player/5")
                        .with(csrf())
                        .with(user("testUser").password("password"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deletePlayerTestNoID() throws Exception {

        mockMvc.perform(delete("/player/null")
                        .with(csrf())
                        .with(user("testUser").password("password"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deletePlayerTestNotExistingID() throws Exception {

        mockMvc.perform(delete("/player/12")
                        .with(csrf())
                        .with(user("testUser").password("password"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


}
