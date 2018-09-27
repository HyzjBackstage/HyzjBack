package hyzj.demo.Controller;

import hyzj.demo.Service.CookiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("")
public class CookiesController {

    @Autowired
    CookiesService cookiesService;

    @RequestMapping("saveCookies")
    @ResponseBody
    public boolean saveCookies(String WXid,  HttpServletResponse response, HttpServletRequest request){
        String WXID = "xs02";

        return cookiesService.saveCookies(WXID,response,request);
    }
    @RequestMapping("/print")
    @ResponseBody
    public String print( HttpServletResponse response, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String ss= "";
        for (Cookie cookie : cookies) {
            System.out.println(cookie.toString());
            if (cookie.getName().equals("WXID")) {
                ss=cookie.getValue();
            }
        }
        return ss;
    }

}
