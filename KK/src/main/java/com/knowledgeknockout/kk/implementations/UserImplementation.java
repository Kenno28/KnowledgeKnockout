package com.knowledgeknockout.kk.implementations;

import com.knowledgeknockout.kk.entity.User;
import com.knowledgeknockout.kk.interfaces.UserInterface;
import com.knowledgeknockout.kk.interfaces.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.data.jpa.repository.JpaRepository;

@Component
public class UserImplementation  implements UserInterface {
    private  UserRepository userRepository;

    @Override
    public boolean register(String Email, String password, String Username) {


        if(Email.isEmpty() || password.isEmpty() || Username.isEmpty()){
            throw new IllegalArgumentException("Email, password or User cannot be empty");
        }

        try {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            password = bCryptPasswordEncoder.encode(password);
            User user = new User(Email, password, Username);
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

    @Override
    public boolean deleteAccount(long id) {
        return false;
    }
}
