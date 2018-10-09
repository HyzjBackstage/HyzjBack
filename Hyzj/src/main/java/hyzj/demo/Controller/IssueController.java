package hyzj.demo.Controller;


import hyzj.demo.Service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/issue")
public class IssueController {

    @Autowired
    IssueService issueService;

    //页面初始化
    @RequestMapping("")
    public String issue(HttpSession httpSession, Map<String, Object> map) {
        System.out.println("优惠券种类数："+issueService.getAllCouponType().size());
        map.put("couponTypes",issueService.getAllCouponType());
//        issueService.setJSSDKConfig(map);
        return "issueCoupons";
    }

    //生成优惠券
    @RequestMapping("/generate")
    @ResponseBody
    public String generate(HttpSession httpSession, String couponType) {
        httpSession.setAttribute("userId", "1000000000");//TODO 发券用户
        return issueService.generateCoupon(httpSession, couponType);
    }

}
