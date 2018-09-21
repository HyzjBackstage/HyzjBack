package hyzj.demo.Controller;

import hyzj.demo.ExceptionEnum.ExceptionEnum;
import hyzj.demo.Model.Coupon;
import hyzj.demo.RsultModel.R_data;
import hyzj.demo.Service.CouponService;
import hyzj.demo.Utils.ResultUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("coupon")
public class CouponController {

    @Autowired
    CouponService couponService;

    @RequestMapping("/discountlist")
    @ResponseBody
    public R_data<List<Coupon>> loadDiscount() throws Exception {
        System.out.println(ResultUtils.success(couponService.loadDiscount(), ExceptionEnum.SUCCESS));
        return ResultUtils.success(couponService.loadDiscount(), ExceptionEnum.SUCCESS);
    }

    @RequestMapping("/discountadd")
    @ResponseBody
    public R_data addDiscount(int cid, int discount, String strat, String end, String condition) throws Exception {
//        System.out.println("cid:"+cid+"--"+"discount:"+discount+"--"+"strat:"+strat+"--"+"end:"+end+"condition:"+condition);
        return ResultUtils.success(couponService.addDiscount(cid, discount, strat, end, condition), ExceptionEnum.SUCCESS);
    }

    @RequestMapping("/discountdelete")
    @ResponseBody
    public R_data deleteDiscount(String coid) throws Exception {
        return ResultUtils.success(couponService.deleteDiscount(coid), ExceptionEnum.SUCCESS);
    }

    @RequestMapping("/discountupdate")
    @ResponseBody
    public R_data updateDiscount(int cid, int discount, String strat, String end, String condition) throws Exception {
        System.out.println("cid:" + cid + "--" + "discount:" + discount + "--" + "strat:" + strat + "--" + "end:" + end + "condition:" + condition);
        return ResultUtils.success(couponService.updateDiscount(cid, discount, strat, end, condition), ExceptionEnum.SUCCESS);
    }

    /**
     * 金额优惠
     */
    @RequestMapping("/amountlist")
    @ResponseBody
    public R_data<List<Coupon>> loadAmount() throws Exception {
        return ResultUtils.success(couponService.loadAmount(), ExceptionEnum.SUCCESS);
    }

    @RequestMapping("/amountadd")
    @ResponseBody
    public R_data addAmount(int cid, int amount, String strat, String end, String condition) throws Exception {
        return ResultUtils.success(couponService.addAmount(cid, amount, strat, end, condition), ExceptionEnum.SUCCESS);
    }

    @RequestMapping("/amountupdate")
    @ResponseBody
    public R_data updateAmount(int cid, int amount, String strat, String end, String condition) throws Exception {
        return ResultUtils.success(couponService.updateAmount(cid, amount, strat, end, condition), ExceptionEnum.SUCCESS);
    }
    @RequestMapping("/amountdelete")
    @ResponseBody
    public R_data deleteAmount(String coid){
        return ResultUtils.success(couponService.deleteAmount(coid),ExceptionEnum.SUCCESS);
    }
}
