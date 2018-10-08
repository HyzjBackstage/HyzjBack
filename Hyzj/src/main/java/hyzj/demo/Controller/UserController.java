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
import java.util.SortedMap;

@Controller
@RequestMapping("")
public class UserController {

    @Autowired
    UserService userService;

//微信登陆

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
            return "login.html";
        }
//        return userVo;
        return "index.html";
    }

    //电脑登录
    @RequestMapping("/MallUserLogin")
    @ResponseBody
    public R_data WindowLogin(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session,String phone,String pwd) throws Exception {
// username = "xs02";
//        UserVo userVo = new UserVo();
//        userService.WindowLogin(request, response, session,phone,pwd)
        System.out.println(phone+":"+pwd);
//        return userVo;
        return ResultUtils.success(userService.WindowLogin(request, response, session,phone,pwd),ExceptionEnum.SUCCESS);
    }

    @RequestMapping("/index")
    public String Transfer(HttpSession session){
        if (session.getAttribute("UserVo") == null){
            return "login.html";
        }
        return "index.html";
    }

    @RequestMapping("/UserOut")
    public String UserOut(HttpSession session){
        userService.UserOut(session);
        return "redirect:/index";
    }



}
