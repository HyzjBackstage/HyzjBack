package hyzj.demo.ModelVo;

import hyzj.demo.Model.Agent;
import hyzj.demo.Model.MallUser;

public class AgentVo {

    private Agent agent;
    private MallUser mallUser;
    private MallUser stationer;


    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public MallUser getMallUser() {
        return mallUser;
    }

    public void setMallUser(MallUser mallUser) {
        this.mallUser = mallUser;
    }

    public MallUser getStationer() {
        return stationer;
    }

    public void setStationer(MallUser stationer) {
        this.stationer = stationer;
    }

    @Override
    public String toString() {
        return "AgentVo{" +
                "agent=" + agent +
                ", mallUser=" + mallUser +
                ", stationer=" + stationer +
                '}';
    }
}
