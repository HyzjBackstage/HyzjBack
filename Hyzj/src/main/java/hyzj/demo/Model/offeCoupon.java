package hyzj.demo.Model;

import java.io.Serializable;

public class offeCoupon implements Serializable{

    private String OFid;
    private String offe_user;
    private String Receiver;
    private String COid;
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

    public String getCOid() {
        return COid;
    }

    public void setCOid(String COid) {
        this.COid = COid;
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
                ", CO_id='" + COid + '\'' +
                ", pickTime='" + pickTime + '\'' +
                ", state='" + state + '\'' +
                ", useTime='" + useTime + '\'' +
                '}';
    }
}
