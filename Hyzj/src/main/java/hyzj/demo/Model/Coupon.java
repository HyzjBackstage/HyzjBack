package hyzj.demo.Model;

import java.sql.Date;

public class Coupon {
    private String Co_id;
    private String amount;
    private String discount;
    private String starting_time;
    private Date end_time;
    private String condition_use;
    private String type;

    public String getCo_id() {
        return Co_id;
    }

    public void setCo_id(String co_id) {
        Co_id = co_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getStarting_time() {
        return starting_time;
    }

    public void setStarting_time(String starting_time) {
        this.starting_time = starting_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public String getCondition_use() {
        return condition_use;
    }

    public void setCondition_use(String condition_use) {
        this.condition_use = condition_use;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "Co_id='" + Co_id + '\'' +
                ", amount='" + amount + '\'' +
                ", discount='" + discount + '\'' +
                ", starting_time='" + starting_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", condition_use='" + condition_use + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

