package ru.task.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.task.entities.Customer;
import ru.task.service.CustomerService;
import ru.task.service.impl.CustomerServiceImpl;
import ru.task.utils.TokenUtil;
import ru.task.utils.UserContextHolder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by mirak on 13.03.17.
 * Фильтр для проверки авторизации
 * Проверяет token, указанный в заголовке Authorization
 * Токен представляет из себя JWT(Json Web Token)
 * По умолчанию токен имеет время жизни 1 день
 */
public class AuthFilter extends OncePerRequestFilter{

    public static final String URL_MATCH = "/rest/";

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (httpServletRequest.getPathInfo().contains(URL_MATCH)) {
            String authHeader = httpServletRequest.getHeader("Authorization");
            if (StringUtils.isEmpty(authHeader)) {
                throw new AuthenticationServiceException("AuthenticationError");
            }

            String[] splitted = authHeader.split(" ");
            if (splitted.length < 2) {
                throw new AuthenticationServiceException("AuthenticationError");
            }

            String token = splitted[1];
            if (StringUtils.isEmpty(token)) {
                throw new AuthenticationServiceException("AuthenticationError");
            }

            DefaultClaims claims;
            try {
                claims = (DefaultClaims) Jwts.parser().setSigningKey(TokenUtil.JWT_KEY).parse(token).getBody();
            } catch (Exception ex) {
                throw new AuthenticationServiceException("Token corrupted");
            }

            if (claims.get("token_expiration_date", Long.class) == null) {
                throw new AuthenticationServiceException("Invalid token");
            }

            Date expiredDate = new Date(claims.get("token_expiration_date", Long.class));
            if (expiredDate.before(new Date())){
                throw new AuthenticationServiceException("Token expired date error");
            }

            UserContextHolder.getData().setLogin(claims.get("login", String.class));
            UserContextHolder.getData().setCustomerId(Long.valueOf(String.valueOf(claims.get("customerId"))));
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
