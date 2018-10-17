package hyzj.demo.Model;

import java.io.Serializable;

public class offeCoupon implements Serializable{

    private String OFid;
    private String offe_user;
    private String Receiver;
    private String CO_id;
    private String pickTime;
    private String state;
    private String useTime;

    public String getOFid() {
        return OFid;
    }

    public void setOFid(String OFid) {
        this.OFid = OFid;
    }

    public String getOffe_user() {
        return offe_user;
    }

    public void setOffe_user(String offe_user) {
        this.offe_user = offe_user;
    }

    public String getReceiver() {
        return Receiver;
    }

    public void setReceiver(String receiver) {
        Receiver = receiver;
    }

    public String getCO_id() {
        return CO_id;
    }

    public void setCO_id(String CO_id) {
        this.CO_id = CO_id;
    }

    public String getPickTime() {
        return pickTime;
    }

    public void setPickTime(String pickTime) {
        this.pickTime = pickTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUseTime() {
        return useTime;
    }

    public void setUseTime(String useTime) {
        this.useTime = useTime;
    }

    @Override
    public String toString() {
        return "offeCoupon{" +
                "OFid='" + OFid + '\'' +
                ", offe_user='" + offe_user + '\'' +
                ", Receiver='" + Receiver + '\'' +
                ", CO_id='" + CO_id + '\'' +
                ", pickTime='" + pickTime + '\'' +
                ", state='" + state + '\'' +
                ", useTime='" + useTime + '\'' +
                '}';
    }
}
