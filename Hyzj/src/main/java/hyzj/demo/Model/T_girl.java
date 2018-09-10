package hyzj.demo.Model;

public class T_girl {
    private Integer id;
    private String cup;
    private Integer height;
    private String name;
    private String sex;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCup() {
        return cup;
    }

    public void setCup(String cup) {
        this.cup = cup;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "T_girl{" +
                "id=" + id +
                ", cup='" + cup + '\'' +
                ", height=" + height +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
