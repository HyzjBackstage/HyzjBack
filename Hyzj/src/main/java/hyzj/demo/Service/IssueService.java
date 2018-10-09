package hyzj.demo.Service;


import hyzj.demo.Dao.CouponDao;
import hyzj.demo.Dao.CouponTypeDao;
import hyzj.demo.Model.Coupon;
import hyzj.demo.Model.CouponType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@Service
public class IssueService {

    @Resource
    CouponTypeDao couponTypeDao;
    @Resource
    CouponDao couponDao;
/*
    //准备JS-SDK权限验证配置所需参数
    public void setJSSDKConfig(Map<String, Object> map) {
        String appId = WeChatUtil.AppID;
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);//时间戳
        String nonceStr = UUID.randomUUID().toString().replace("-", "").substring(0, 16);
        String jsapi_ticket = WeChatUtil.jsapi_ticket;
        String url = "http://test.trunch.cn/coupon/issue";
        String signature = WeChatUtil.getJSSDKSignature(jsapi_ticket, nonceStr, timestamp, url);

//        httpSession.setAttribute("appId", appId);
//        httpSession.setAttribute("timestamp", timestamp);
//        httpSession.setAttribute("nonceStr", nonceStr);
//        httpSession.setAttribute("signature", signature);
        map.put("appId", appId);
        map.put("timestamp", timestamp);
        map.put("nonceStr", nonceStr);
        map.put("signature", signature);

        System.out.println("jsapi_ticket:" + jsapi_ticket
                + "\nnoncestr:" + nonceStr
                + "\ntimestamp:" + timestamp
                + "\nurl:" + url);
        System.out.println("JS-SDK配置签名：" + signature + "\n--------------------------------\n");
    }
  */

    //获得所有优惠券种类
    public List<CouponType> getAllCouponType() {
        return couponTypeDao.getAllCouponType();
    }

    //生成一张优惠券
    public String generateCoupon(HttpSession httpSession, String couponType) {
        String userId = (String) httpSession.getAttribute("userId");
        String couponId = UUID.randomUUID().toString().replace("-", "");
        couponDao.createNewCoupon(new Coupon(couponId, userId, couponType));
        System.out.println("随机生成优惠券：" + couponId);
        return couponId;
    }
}
