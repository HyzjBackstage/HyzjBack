package hyzj.demo.Model;

import java.io.Serializable;

public class logistics_company implements Serializable {

    private String LC_id;       //快递公司编号
    private String name;        //快递公司名
    private String url;         //快递公司官网url

    public String getLC_id() {
        return LC_id;
    }

    public void setLC_id(String LC_id) {
        this.LC_id = LC_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "T_logistics_company{" +
                "LC_id='" + LC_id + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
