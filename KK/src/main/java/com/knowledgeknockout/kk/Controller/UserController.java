package com.knowledgeknockout.kk.Controller;

import com.knowledgeknockout.kk.entity.User;
import com.knowledgeknockout.kk.ep.UserEP;
import com.knowledgeknockout.kk.interfaces.UserInterface;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import jakarta.validation.Valid;

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
    public ResponseEntity<?> createPlayer(@Valid User user) {
        if(user == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is null");
        }

        String passwordTest = user.getPassword();
        String regex = "^(?=.*[A-Z])(?=.*[!@#$%^&*(),.?\":{}|<>])(?=.*\\d).{8,}$";
        Pattern pattern = Pattern.compile(regex);

        if (!pattern.matcher(passwordTest).matches()) {
            return ResponseEntity.status(400).body("Password did not satisfy the requirement");
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

    /**
     * @param user
     * @return
     */
    @Override
    public ResponseEntity<?> updatePlayer(User user) {
        if(user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is null");
        }

        if(!user.getPassword().startsWith("$2a$")) {
            String passwordTest = user.getPassword();
            String regex = "^(?=.*[A-Z])(?=.*[!@#$%^&*(),.?\":{}|<>])(?=.*\\d).{8,}$";
            Pattern pattern = Pattern.compile(regex);

            if (!pattern.matcher(passwordTest).matches()) {
                return ResponseEntity.status(400).body("Password did not satisfy the requirement");
            }

            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }

        try {
            ui.UpdateUser(user);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<?> deletePlayer(Integer id) {
        if(id == null || id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID is null");
        }

        try {
            ui.deleteUser(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<?> getPlayer(Integer id) {
        if(id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is null");
        }

        try {
            ui.getUser(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
}
