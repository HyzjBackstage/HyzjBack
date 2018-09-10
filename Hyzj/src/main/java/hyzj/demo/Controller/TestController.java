package hyzj.demo.Controller;

import hyzj.demo.Anotation.Privilege;
import hyzj.demo.ExceptionEnum.ExceptionEnum;
import hyzj.demo.Model.T_girl;
import hyzj.demo.RsultModel.R_data;
import hyzj.demo.Service.TestServive;
import hyzj.demo.Utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@EnableAutoConfiguration
public class TestController {

    @Autowired
    TestServive testServive;
    //
    @RequestMapping("/")
    public String index(Model model, HttpServletResponse response) {
        model.addAttribute("name", "simonsfan");
        return "/index";
    }

//    @Privilege("查找全部女生")
//    @GetMapping("/loadgirl")
//    @ResponseBody
//    public R_data<List<T_girl>> loadgirl() throws Exception {
//
//        return ResultUtils.success(testServive.loadGirl(), ExceptionEnum.SUCCESS);
//    }
//    //跳转到个人中心界面
//    @Privilege("查找全部女生")
    @RequestMapping(value = "{pageName2}")
    public ModelAndView toPage(@PathVariable("pageName2") String pageName2) {
        ModelAndView mv = new ModelAndView(pageName2);
        return mv;
    }

}

