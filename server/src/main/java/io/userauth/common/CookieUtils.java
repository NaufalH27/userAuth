package io.userauth.common;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class CookieUtils {
    
    public static void sendCookie(HttpServletResponse response, String cookieName, String item) {
        Cookie cookie = new Cookie(cookieName, item);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60 * 24);
        cookie.setPath("/");
        cookie.setSecure(true);
        response.addCookie(cookie);
    }


    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
    public static void eraseCookie(String cookieName, HttpServletResponse response) {
        Cookie cookie = new Cookie(cookieName, "");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setSecure(true);
        response.addCookie(cookie);
    }

}



