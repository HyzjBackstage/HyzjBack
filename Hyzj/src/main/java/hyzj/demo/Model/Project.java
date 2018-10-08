package hyzj.demo.Model;

public class Project {
    private String P_id;
    private String name;
    private String funding;
    private float discount_lowest;
    private float discount_highest;

    public String getP_id() {
        return P_id;
    }

    public void setP_id(String p_id) {
        P_id = p_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFunding() {
        return funding;
    }

    public void setFunding(String funding) {
        this.funding = funding;
    }

    public float getDiscount_lowest() {
        return discount_lowest;
    }

    public void setDiscount_lowest(float discount_lowest) {
        this.discount_lowest = discount_lowest;
    }

    public float getDiscount_highest() {
        return discount_highest;
    }

    public void setDiscount_highest(float discount_highest) {
        this.discount_highest = discount_highest;
    }

    @Override
    public String toString() {
        return "Project{" +
                "P_id='" + P_id + '\'' +
                ", name='" + name + '\'' +
                ", funding='" + funding + '\'' +
                ", discount_lowest=" + discount_lowest +
                ", discount_highest=" + discount_highest +
                '}';
    }
}
