package com.duoshenyi.backend.api.controller;

import com.duoshenyi.backend.user.ex.UserExistsException;
import com.duoshenyi.backend.user.model.User;
import com.duoshenyi.backend.user.service.UserManager;
import com.google.gson.JsonObject;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.password.PasswordEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wuyaohui on 14-11-11.
 */
@RestController
@RequestMapping(value = {"/users"},produces = {"application/json"})
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private UserManager userManager;

    @Autowired
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    @RequestMapping(method = {RequestMethod.POST})
    public ResponseEntity<String> userAdd(@RequestParam(required = false) String name,
                                          @RequestParam String account,
                                          @RequestParam String password){
        if(password==null||password.isEmpty()){
            return new ResponseEntity<>("password can't be null or empty",HttpStatus.BAD_REQUEST);
        }
        if(account==null||account.isEmpty()){
            return new ResponseEntity<>("account can't be null or empty",HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setName(name);
        user.setAccount(account);

        PasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        String encryptedText = passwordEncryptor.encryptPassword(password);
        user.setPassword(encryptedText.getBytes());

        User addedU = null;
        try{
            addedU = userManager.addUser(user);
        }catch (UserExistsException uee){
            String respMsg = "user with account["+user.getAccount()+"] already exists.";
            LOG.warn(respMsg);
            return new ResponseEntity<>(respMsg,HttpStatus.BAD_REQUEST);
        }

        JsonObject jsonUser = new JsonObject();
        jsonUser.addProperty("id",addedU.getId());

        return new ResponseEntity<>(jsonUser.toString(),HttpStatus.OK);
    }

    @RequestMapping(value = {"/login"},method = {RequestMethod.POST})
    public ResponseEntity<String> signIn(@RequestParam String account,
                                          @RequestParam String password){
        if(password==null||password.isEmpty()){
            return new ResponseEntity<>("password can't be null or empty",HttpStatus.BAD_REQUEST);
        }
        if(account==null||account.isEmpty()){
            return new ResponseEntity<>("account can't be null or empty",HttpStatus.BAD_REQUEST);
        }
        User user = userManager.getUserByAccount(account);
        if(user==null){
            return new ResponseEntity<>("User "+account+" not exists.",HttpStatus.BAD_REQUEST);
        }
        PasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        if(passwordEncryptor.checkPassword(password,user.getStringPassword())){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("account or password error",HttpStatus.BAD_REQUEST);
    }
}
