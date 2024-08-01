package com.knowledgeknockout.kk.implementations;

import com.knowledgeknockout.kk.entity.User;
import com.knowledgeknockout.kk.interfaces.UserInterface;

public class UserImplementation implements UserInterface {

    @Override
    public boolean register(String Email, String password, String Username) {
        return false;
    }


    @Override
    public boolean login(String username, String password) {
        return false;
    }

    @Override
    public boolean logout() {
        return false;
    }

    @Override
    public void UpdateUser(User user) {

    }
}
