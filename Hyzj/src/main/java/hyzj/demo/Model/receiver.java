package hyzj.demo.Model;

public class receiver {

    private String Re_id;               //收货人id
    private String name;                //收货人姓名
    private String phone;               //联系电话
    private String acquiescence;        //是否默认
    private String province;            //省
    private String city;                //市
    private String county;              //县
    private String address_details;     //地址详情

    public String getRe_id() {
        return Re_id;
    }

    public void setRe_id(String re_id) {
        Re_id = re_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAcquiescence() {
        return acquiescence;
    }

    public void setAcquiescence(String acquiescence) {
        this.acquiescence = acquiescence;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddress_details() {
        return address_details;
    }

    public void setAddress_details(String address_details) {
        this.address_details = address_details;
    }

    @Override
    public String toString() {
        return "T_receiver{" +
                "Re_id='" + Re_id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", acquiescence='" + acquiescence + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", address_details='" + address_details + '\'' +
                '}';
    }
}
