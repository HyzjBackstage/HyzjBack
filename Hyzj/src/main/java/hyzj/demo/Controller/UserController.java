package hyzj.demo.Controller;


import com.alibaba.druid.support.json.JSONUtils;
import hyzj.demo.Exception.DataLinkException;
import hyzj.demo.ExceptionEnum.ExceptionEnum;
import hyzj.demo.ModelVo.UserVo;
import hyzj.demo.RsultModel.R_data;
import hyzj.demo.Service.UserService;
import hyzj.demo.Utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping("/Hyzj")
//    @ResponseBody
    public String login(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
// username = "xs02";
//        UserVo userVo = new UserVo();
        try {
            UserVo userVo = userService.login(request, response, session);
            System.out.println(userVo);
//            model.addAttribute("UserVo", userVo);
            //将数据保存到session中前台th使用
            session.setAttribute("UserVo", userVo);

//            model.addAttribute("user","user");
//            session.setAttribute("user","user");
        } catch (Exception e) {
//            throw  new DataLinkException(e, ExceptionEnum.DATALINK_Exception);
            return "404.html";
        }
//        return userVo;
        return "index.html";
    }

    @RequestMapping("/index")
    public String index(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
// username = "xs02";
//        UserVo userVo = new UserVo();
        try {
            UserVo userVo = userService.login(request, response, session);
            System.out.println(userVo);
//            model.addAttribute("UserVo", userVo);
            //将数据保存到session中前台th使用
            session.setAttribute("UserVo", userVo);

//            model.addAttribute("user","user");
//            session.setAttribute("user","user");
        } catch (Exception e) {
//            throw  new DataLinkException(e, ExceptionEnum.DATALINK_Exception);
            return "404.html";
        }
//        return userVo;
        return "index.html";
    }




}
