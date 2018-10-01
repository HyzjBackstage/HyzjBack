package hyzj.demo.Model;

import java.io.Serializable;

public class logistics implements Serializable {

    private String L_id;                //物流id
    private String LC_id;               //物流公司表id
    private String logistics_company;   //物流公司名
    private String shipment_number;     //物流单号
    private String amount;              //快递费

    public String getL_id() {
        return L_id;
    }

    public void setL_id(String l_id) {
        L_id = l_id;
    }

    public String getLC_id() {
        return LC_id;
    }

    public void setLC_id(String LC_id) {
        this.LC_id = LC_id;
    }

    public String getLogistics_company() {
        return logistics_company;
    }

    public void setLogistics_company(String logistics_company) {
        this.logistics_company = logistics_company;
    }

    public String getShipment_number() {
        return shipment_number;
    }

    public void setShipment_number(String shipment_number) {
        this.shipment_number = shipment_number;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "T_logistics{" +
                "L_id='" + L_id + '\'' +
                ", LC_id='" + LC_id + '\'' +
                ", logistics_company='" + logistics_company + '\'' +
                ", shipment_number='" + shipment_number + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
