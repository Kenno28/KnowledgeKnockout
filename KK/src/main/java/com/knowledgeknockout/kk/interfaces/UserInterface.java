package com.knowledgeknockout.kk.interfaces;

import com.knowledgeknockout.kk.entity.User;

public interface UserInterface {

    boolean register(String Email, String password, String Username);

    boolean login(String username, String password);

    boolean logout();

    void UpdateUser(User user);

}
