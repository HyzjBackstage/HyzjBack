package hyzj.demo.ModelVo;

public class StockVo {
       private String C_id ;
       private String stock;
       private String name;

    public String getC_id() {
        return C_id;
    }

    public void setC_id(String c_id) {
        C_id = c_id;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "StockVo{" +
                "C_id='" + C_id + '\'' +
                ", stock='" + stock + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
