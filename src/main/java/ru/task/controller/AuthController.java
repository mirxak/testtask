package ru.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.task.utils.JsonUtils;
import ru.task.utils.TokenUtil;

import java.io.IOException;

/**
 * Created by mirak on 12.03.17.
 */
@Controller
@RequestMapping(value = "auth", produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    @Autowired
    TokenUtil tokenUtil;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestBody String json){
        LoginJson loginJson;
        try {
            loginJson = JsonUtils.getFromJson(json, LoginJson.class);
        } catch (IOException e) {
            throw new RuntimeException("Json incorrect");
        }

        return tokenUtil.getToken(loginJson.getLogin(), loginJson.getPassword());
    }

//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    public void logout(){
//
//    }


    public static class LoginJson {
        private String login;
        private String password;

        public void setLogin(String login) {
            this.login = login;
        }

        public String getLogin(){
            return login;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPassword(){
            return password;
        }
    }
}
