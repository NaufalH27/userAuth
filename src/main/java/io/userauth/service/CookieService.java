package io.userauth.service;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;


@Service
public class CookieService {
    
    public void sendToken(HttpServletResponse serverResponse, String token) {
        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60 * 24);
        serverResponse.addCookie(cookie);
    }

}



