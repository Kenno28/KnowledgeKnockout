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
            e.printStackTrace();
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
        if(user.getEmailAddress().isEmpty() || user.getPassword().isEmpty() || user.getUsername().isEmpty()){
            throw new IllegalArgumentException("Email, password or Username cannot be empty");
        }else if(user.getId() == null){
            throw new IllegalArgumentException("Id cannot be null");
        }

        try {
            if(userRepository.existsById(user.getId())){
                userRepository.save(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean deleteUser(int id) {
        if(!userRepository.existsById(id)){
            throw new IllegalArgumentException("ID not found");
        }

        try {
            userRepository.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public User getUser(int id) {
        if(!userRepository.existsById(id)){
            throw new IllegalArgumentException("ID not found");
        }

        return userRepository.findById(id).get();
    }



}
