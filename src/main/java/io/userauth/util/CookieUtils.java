package io.userauth.util;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Service
public class CookieUtils {
    
    public static void sendCookies(HttpServletResponse response, String cookieName, String item) {
        Cookie cookie = new Cookie(cookieName, item);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60 * 24);
        cookie.setPath("/");
        response.addCookie(cookie);
    }


    public static Cookie getCookieValue(HttpServletRequest request, String CookieName){
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (CookieName.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }
        return null;
    }

}



