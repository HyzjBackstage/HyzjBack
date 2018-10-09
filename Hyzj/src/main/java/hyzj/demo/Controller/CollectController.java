package hyzj.demo.Controller;

import hyzj.demo.Service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    CollectService collectService;

    //页面初始化
    @RequestMapping("")
    public String collect(HttpSession httpSession, String couponId) {
        httpSession.setAttribute("couponId", couponId);
        return "collectCoupons";
    }

    //优惠券领取绑定手机号
    @RequestMapping("/bind")
    @ResponseBody
    public String bind(HttpSession httpSession, String phoneNum) {
        return collectService.bindPhoneNum(httpSession, phoneNum);
    }
}
