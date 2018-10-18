package hyzj.demo.Model;

public class Commodity {
    private String C_id;
    private String Y_id;//规格
    private String name;//商品名
    private String productionDate;//生产日期
    private String shelfDate;
    private String image;
    private float price;//售价
    private String comment;//评论
    private float purchase_price;//进价
    private String stock; //库存
    private String addTime;//商品加入的时间
    private int state;

    public String getC_id() {
        return C_id;
    }

    public void setC_id(String c_id) {
        C_id = c_id;
    }

    public String getY_id() {
        return Y_id;
    }

    public void setY_id(String y_id) {
        Y_id = y_id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public String getShelfDate() {
        return shelfDate;
    }

    public void setShelfDate(String shelfDate) {
        this.shelfDate = shelfDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public float getPurchase_price() {
        return purchase_price;
    }

    public void setPurchase_price(float purchase_price) {
        this.purchase_price = purchase_price;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "C_id='" + C_id + '\'' +
                ", Y_id='" + Y_id + '\'' +
                ", name='" + name + '\'' +
                ", productionDate='" + productionDate + '\'' +
                ", shelfDate='" + shelfDate + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", comment='" + comment + '\'' +
                ", purchase_price=" + purchase_price +
                ", stock='" + stock + '\'' +
                ", addTime='" + addTime + '\'' +
                ", state=" + state +
                '}';
    }
}

