package hyzj.demo.Model;

import java.io.Serializable;

public class FactoryRebate implements Serializable{

    private String F_id;
    private String user_id;
    private String project_id;
    private String rebate_time;
    private String rebate_amount;

    public String getF_id() {
        return F_id;
    }

    public void setF_id(String f_id) {
        F_id = f_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getRebate_time() {
        return rebate_time;
    }

    public void setRebate_time(String rebate_time) {
        this.rebate_time = rebate_time;
    }

    public String getRebate_amount() {
        return rebate_amount;
    }

    public void setRebate_amount(String rebate_amount) {
        this.rebate_amount = rebate_amount;
    }

    @Override
    public String toString() {
        return "FactoryRebate{" +
                "F_id='" + F_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", project_id='" + project_id + '\'' +
                ", rebate_time='" + rebate_time + '\'' +
                ", rebate_amount='" + rebate_amount + '\'' +
                '}';
    }
}
