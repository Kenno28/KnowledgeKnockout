package com.knowledgeknockout.kk.Controller;

import com.knowledgeknockout.kk.entity.User;
import com.knowledgeknockout.kk.ep.UserEP;
import com.knowledgeknockout.kk.interfaces.UserInterface;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.regex.Pattern;

@RestController
public class UserController implements UserEP {

    @Autowired
    private UserInterface ui;

    /**
     * @param user
     * @return
     */
    @Override
    public ResponseEntity<?> createPlayer(User user) {
        if(user == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is null");
        }

        String passwordTest = user.getPassword();
        String regex = "^(?=.*[A-Z])(?=.*[!@#$%^&*(),.?\":{}|<>])(?=.*\\d).{8,}$";
        Pattern pattern = Pattern.compile(regex);

        if (!pattern.matcher(passwordTest).matches()) {
            return ResponseEntity.status(400).build();
        }

        try {
            Boolean registered= ui.register(user);

            if(registered){
                return ResponseEntity.status(HttpStatus.CREATED).build();
            } else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }catch (DataAccessException dax){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(dax);
        }
    }
}
