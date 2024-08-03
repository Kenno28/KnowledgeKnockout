package com.knowledgeknockout.kk.implementations;

import com.knowledgeknockout.kk.entity.User;
import com.knowledgeknockout.kk.interfaces.UserInterface;
import com.knowledgeknockout.kk.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.data.jpa.repository.JpaRepository;

@Component
public class UserImplementation  implements UserInterface {
    @Autowired
    private  UserRepository userRepository;

    @Override
    public boolean register(User user) {

        if(user.getEmailAddress().isEmpty() || user.getPassword().isEmpty() || user.getUsername().isEmpty()){
            throw new IllegalArgumentException("Email, password or Username cannot be empty");
        }

        try {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
        }catch (Exception e){
            return false;
        }
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
