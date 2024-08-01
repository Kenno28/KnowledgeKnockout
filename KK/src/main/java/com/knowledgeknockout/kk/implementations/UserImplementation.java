package com.knowledgeknockout.kk.implementations;

import com.knowledgeknockout.kk.entity.User;
import com.knowledgeknockout.kk.interfaces.UserInterface;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
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
