package ru.itis.usanov.helper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CookieHelper {

    public static void cookieCheck(HttpServletResponse resp, String username, String cookieCheck, HttpSession session) throws IOException {

        if(cookieCheck != null && cookieCheck.equals("check")){
            Cookie cookie = new Cookie("saved", username);
            cookie.setMaxAge(100);
            resp.addCookie(cookie);
        }

        resp.sendRedirect("/homepage");
        session.setAttribute("username", username);
    }
}
