package hyzj.demo.Model;

public class Agent {
    private String Mid;
    private String addTime;
    private String discount;
    private String stationer;

    public String getMid() {
        return Mid;
    }

    public void setMid(String mid) {
        Mid = mid;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getStationer() {
        return stationer;
    }

    public void setStationer(String stationer) {
        this.stationer = stationer;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "Mid='" + Mid + '\'' +
                ", addTime='" + addTime + '\'' +
                ", discount='" + discount + '\'' +
                ", stationer='" + stationer + '\'' +
                '}';
    }
}
