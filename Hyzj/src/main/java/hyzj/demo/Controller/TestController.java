package hyzj.demo.Controller;

import hyzj.demo.Service.TestServive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("")
public class TestController {

    @Autowired
    TestServive testServive;
    //
//    @RequestMapping("indexss")
//    public String index(Model model, HttpServletResponse response) {
//        model.addAttribute("name", "simonsfan");
//        return "jsp/indexss";
//    }

//    @Privilege("查找全部女生")
//    @GetMapping("/loadgirl")
//    @ResponseBody
//    public R_data<List<T_girl>> loadgirl() throws Exception {
//
//        return ResultUtils.success(testServive.loadGirl(), ExceptionEnum.SUCCESS);
//    }
//    //跳转到个人中心界面
//    @Privilege("查找全部女生")
//    @RequestMapping(value = "{pageName2}")
//    public ModelAndView toPage(@PathVariable("pageName2") String pageName2) {
//        ModelAndView mv = new ModelAndView(pageName2);
//        return mv;
//    }

}

