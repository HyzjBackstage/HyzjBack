package hyzj.demo.Model;

import java.io.Serializable;

public class shopping_cart implements Serializable{
    private String SC_id;

    public String getSC_id() {
        return SC_id;
    }

    public void setSC_id(String SC_id) {
        this.SC_id = SC_id;
    }

    @Override
    public String toString() {
        return "shopping_cart{" +
                "SC_id='" + SC_id + '\'' +
                '}';
    }
}
