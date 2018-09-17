package hyzj.demo.Model;

public class Year {
    private String Y_id;
    private String  yaer;

    public String getY_id() {
        return Y_id;
    }

    public void setY_id(String y_id) {
        Y_id = y_id;
    }

    public String getYaer() {
        return yaer;
    }

    public void setYaer(String yaer) {
        this.yaer = yaer;
    }

    @Override
    public String toString() {
        return "Year{" +
                "Y_id='" + Y_id + '\'' +
                ", yaer='" + yaer + '\'' +
                '}';
    }
}

