package hyzj.demo.ModelVo;

import hyzj.demo.Model.*;

public class OrderVo {

    private orders t_orders;
    private receiver t_receiver;
    private MallUser t_mallUser;
    private CouponType couponType;

    public orders getT_orders() {
        return t_orders;
    }

    public void setT_orders(orders t_orders) {
        this.t_orders = t_orders;
    }

    public receiver getT_receiver() {
        return t_receiver;
    }

    public void setT_receiver(receiver t_receiver) {
        this.t_receiver = t_receiver;
    }

    public MallUser getT_mallUser() {
        return t_mallUser;
    }

    public void setT_mallUser(MallUser t_mallUser) {
        this.t_mallUser = t_mallUser;
    }

    public CouponType getCoupon() {
        return couponType;
    }

    public void setCouponType(CouponType couponType) {
        this.couponType = couponType;
    }

    @Override
    public String toString() {
        return "OrderVo{" +
                "t_orders=" + t_orders +
                ", t_receiver=" + t_receiver +
                ", t_mallUser=" + t_mallUser +
                ", couponType=" + couponType +
                '}';
    }
}
