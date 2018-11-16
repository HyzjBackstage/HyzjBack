package hyzj.demo.Model;

import java.io.Serializable;

public class Projecct_wallet implements Serializable{
    private String wallet_id;
    private String user_id;
    private String project_id;
    private String wallet_amount;

    public String getWallet_id() {
        return wallet_id;
    }

    public void setWallet_id(String wallet_id) {
        this.wallet_id = wallet_id;
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

    public String getWallet_amount() {
        return wallet_amount;
    }

    public void setWallet_amount(String wallet_amount) {
        this.wallet_amount = wallet_amount;
    }

    @Override
    public String toString() {
        return "Projecct_wallet{" +
                "wallet_id='" + wallet_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", project_id='" + project_id + '\'' +
                ", wallet_amount='" + wallet_amount + '\'' +
                '}';
    }
}
