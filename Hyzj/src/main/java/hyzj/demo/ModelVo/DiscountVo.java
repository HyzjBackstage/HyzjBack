package hyzj.demo.ModelVo;

import hyzj.demo.Model.Project;

import java.util.List;

public class DiscountVo {

    private List discunt;
    private Project project;

    public List getDiscunt() {
        return discunt;
    }

    public void setDiscunt(List discunt) {
        this.discunt = discunt;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "DiscountVo{" +
                "discunt=" + discunt +
                ", project=" + project +
                '}';
    }
}
