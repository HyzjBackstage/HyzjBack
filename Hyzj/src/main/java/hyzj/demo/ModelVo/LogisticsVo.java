package hyzj.demo.ModelVo;

import hyzj.demo.Model.*;

import java.io.Serializable;

public class LogisticsVo implements Serializable{
    private logistics t_logistics;
    private logistics_company t_logistics_company;
    private orders t_orders;
    private MallUser t_mallUser;
    private receiver t_receiver;

    public logistics getT_logistics() {
        return t_logistics;
    }

    public void setT_logistics(logistics t_logistics) {
        this.t_logistics = t_logistics;
    }

    public logistics_company getT_logistics_company() {
        return t_logistics_company;
    }

    public void setT_logistics_company(logistics_company t_logistics_company) {
        this.t_logistics_company = t_logistics_company;
    }

    public orders getT_orders() {
        return t_orders;
    }

    public void setT_orders(orders t_orders) {
        this.t_orders = t_orders;
    }

    public MallUser getT_mallUser() {
        return t_mallUser;
    }

    public void setT_mallUser(MallUser t_mallUser) {
        this.t_mallUser = t_mallUser;
    }

    public receiver getT_receiver() {
        return t_receiver;
    }

    public void setT_receiver(receiver t_receiver) {
        this.t_receiver = t_receiver;
    }

    @Override
    public String toString() {
        return "LogisticsVo{" +
                "t_logistics=" + t_logistics +
                ", t_logistics_company=" + t_logistics_company +
                ", t_orders=" + t_orders +
                ", t_mallUser=" + t_mallUser +
                ", t_receiver=" + t_receiver +
                '}';
    }
}
