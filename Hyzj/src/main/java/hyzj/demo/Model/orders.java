package hyzj.demo.Model;

import java.io.Serializable;

public class orders implements Serializable{
    private String O_id;            //订单编号
    private String Re_id;           //收货人id
    private String M_id;            //商城用户id（发货人）
    private String Co_id;           //优惠券id
    private String price;           //总价
    private String order_time;      //下单时间
    private String self_lifting;    //是否自提
    private String status;          //订单状态      0-未支付  1-已支付  2-待发货  3-已发货  4-已完成  5-订单已取消

    public String getO_id() {
        return O_id;
    }

    public void setO_id(String o_id) {
        O_id = o_id;
    }

    public String getRe_id() {
        return Re_id;
    }

    public void setRe_id(String re_id) {
        Re_id = re_id;
    }

    public String getM_id() {
        return M_id;
    }

    public void setM_id(String m_id) {
        M_id = m_id;
    }

    public String getCo_id() {
        return Co_id;
    }

    public void setCo_id(String co_id) {
        Co_id = co_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public String getSelf_lifting() {
        return self_lifting;
    }

    public void setSelf_lifting(String self_lifting) {
        this.self_lifting = self_lifting;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "T_orders{" +
                "O_id='" + O_id + '\'' +
                ", Re_id='" + Re_id + '\'' +
                ", M_id='" + M_id + '\'' +
                ", Co_id='" + Co_id + '\'' +
                ", price='" + price + '\'' +
                ", order_time='" + order_time + '\'' +
                ", self_lifting='" + self_lifting + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
