package hyzj.demo.ModelVo;

import hyzj.demo.Model.Commodity;
import hyzj.demo.Model.Year;

public class CommodityVo {
    private Commodity commodity;
    private Year year;

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "CommodityVo{" +
                "commodity=" + commodity +
                ", year=" + year +
                '}';
    }
}
