package hyzj.demo.Model;

import java.io.Serializable;

public class Income implements Serializable{
    private String In_id;
    private String M_id;
    private String time;
    private String income_amount;
    private String unallocated_amount;

    public String getIn_id() {
        return In_id;
    }

    public void setIn_id(String in_id) {
        In_id = in_id;
    }

    public String getM_id() {
        return M_id;
    }

    public void setM_id(String m_id) {
        M_id = m_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIncome_amount() {
        return income_amount;
    }

    public void setIncome_amount(String income_amount) {
        this.income_amount = income_amount;
    }

    public String getUnallocated_amount() {
        return unallocated_amount;
    }

    public void setUnallocated_amount(String unallocated_amount) {
        this.unallocated_amount = unallocated_amount;
    }

    @Override
    public String toString() {
        return "Income{" +
                "In_id='" + In_id + '\'' +
                ", M_id='" + M_id + '\'' +
                ", time='" + time + '\'' +
                ", income_amount='" + income_amount + '\'' +
                ", unallocated_amount='" + unallocated_amount + '\'' +
                '}';
    }
}
