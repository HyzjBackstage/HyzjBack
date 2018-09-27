package hyzj.demo.Model;

public class MenuRole {

    private String M_id;
    private String R_id;

    public String getM_id() {
        return M_id;
    }

    public void setM_id(String m_id) {
        M_id = m_id;
    }

    public String getR_id() {
        return R_id;
    }

    public void setR_id(String r_id) {
        R_id = r_id;
    }

    @Override
    public String toString() {
        return "MenuRole{" +
                "M_id='" + M_id + '\'' +
                ", R_id='" + R_id + '\'' +
                '}';
    }
}
