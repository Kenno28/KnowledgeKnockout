package com.knowledgeknockout.kk.interfaces;

import com.knowledgeknockout.kk.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInterface {

    boolean register(User user);

    boolean login(String username, String password);

    boolean logout();

    void UpdateUser(User user);

    boolean deleteAccount(long id);

}

