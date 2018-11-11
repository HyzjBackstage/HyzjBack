package hyzj.demo.Service;


import hyzj.demo.Dao.CouponDao;
import hyzj.demo.Dao.CouponTypeDao;
import hyzj.demo.Model.Coupon;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;

@Service
public class CollectService {

    @Resource
    CouponTypeDao couponTypeDao;
    @Resource
    CouponDao couponDao;

    //领取优惠券绑定手机号
    public String bindPhoneNum(HttpSession httpSession, String phoneNum) {
        String couponId = (String) httpSession.getAttribute("couponId");
        Timestamp collectTime = new Timestamp(new Date().getTime());
        String couponState = "0";//0表示领取了，未使用。null表示未领取。
        Coupon coupon = couponDao.getCouponById(couponId);
        if (coupon == null) {
            return "2";
        }
        if (coupon.getReceiver() != null && !"".equals(coupon.getReceiver())) {
            System.out.println("??????????????????????????"+coupon.getReceiver()+couponId);
            if (coupon.getReceiver().equals(phoneNum))
                return "3";
            else
                return "2";
        }
        couponDao.bindPhoneNum(new Coupon(couponId, phoneNum, collectTime, couponState));
        return "0";
    }
}
