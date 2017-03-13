package ru.task.utils;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.task.entities.Customer;
import ru.task.service.CustomerService;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mirak on 13.03.17.
 * Получение токена пользователя на основании его данных
 */
@Service
public class TokenUtil {

    public static final String JWT_KEY = "testtask";

    @Autowired
    CustomerService customerService;

    /**
     * Формирует токен с помощью Json Web Token
     * @param login логин пользователя
     * @param password пароль пользователя
     * @return токен в формате JWT
     */
    public String getToken(String login, String password) {
        if (StringUtils.isEmpty(login) || StringUtils.isEmpty(password)){
            return null;
        }

        Customer customer = customerService.getByLogin(login);
        if (customer == null){
            return null;
        }

        if (customer.getStatus().equals(1)){
            throw new RuntimeException("Customer blocked");
        }

        if (!password.equals(customer.getPassword())){
            throw new RuntimeException("Authentication error");
        }

        Map<String, Object> tokenData = new HashMap<>();
        tokenData.put("customerId", customer.getId());
        tokenData.put("login", login);
        tokenData.put("token_create_date", new Date().getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        tokenData.put("token_expiration_date", calendar.getTimeInMillis());
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setExpiration(calendar.getTime());
        jwtBuilder.setClaims(tokenData);

        return jwtBuilder.signWith(SignatureAlgorithm.HS512, JWT_KEY).compact();
    }
}
