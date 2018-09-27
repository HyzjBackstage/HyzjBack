package hyzj.demo.Service;

import com.sun.deploy.net.URLEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class CookiesService {

    public boolean saveCookies(String WXid,  HttpServletResponse response, HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("WXID")) {
               cookie.setMaxAge(0);
            }
        }

        try{
            Cookie WXIDCookie = new Cookie("WXID", WXid);
            WXIDCookie.setMaxAge(86400);//设置cookie生存时间：
            response.addCookie(WXIDCookie);// 添加cookie：
        }catch(Exception e){
            return false;

        }
        return true;
    }
}
