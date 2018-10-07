package hyzj.demo.Controller;

import hyzj.demo.Service.CookiesService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("")
public class CookiesController {

    @Autowired
    CookiesService cookiesService;

    @RequestMapping("save")
    @ResponseBody
    public boolean saveCookies(@RequestParam("mid") String MID, HttpServletResponse response, HttpServletRequest request) {

//        String WXID = "xs02";
        System.out.println(MID);
        return cookiesService.saveCookies(MID, response, request);
    }

    @RequestMapping("print")
    @ResponseBody
    public String print(HttpServletResponse response, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String ss = "";
        for (Cookie cookie : cookies) {
            System.out.println(cookie.toString());
            if (cookie.getName().equals("MID")) {
                ss = cookie.getValue();
            }
        }
        return ss;
    }

    @RequestMapping("clear")
    @ResponseBody
    public boolean clearCookies(HttpServletResponse response, HttpServletRequest request) {
        return cookiesService.clear(response, request);
    }

}
