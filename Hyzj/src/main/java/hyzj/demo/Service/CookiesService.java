package hyzj.demo.Service;

import com.sun.deploy.net.URLEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class CookiesService<T> {

    public boolean saveCookies(String MID,  HttpServletResponse response, HttpServletRequest request) {
        try{
        Cookie[] cookies = request.getCookies();
        if (cookies == null){

            Cookie WXIDCookie = new Cookie("MID", MID);
            WXIDCookie.setMaxAge(86400);//设置cookie生存时间：
            response.addCookie(WXIDCookie);// 添加cookie：
        }
        else {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("MID")) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }

        }
            Cookie WXIDCookie = new Cookie("MID", MID);
            WXIDCookie.setMaxAge(86400);//设置cookie生存时间：
            response.addCookie(WXIDCookie);// 添加cookie：
        }catch(Exception e){
            return false;

        }
        return true;
    }

    public boolean clear( HttpServletResponse response, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("MID")) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }

        return true;
    }


    public  T printCookies(HttpServletRequest request,String cookiesName){
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        } else {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookiesName)) {

                    return (T)cookie.getValue();
                }
                return null;
            }
            return null;
        }
    }
}
