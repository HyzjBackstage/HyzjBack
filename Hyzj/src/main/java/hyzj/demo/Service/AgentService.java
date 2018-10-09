package hyzj.demo.Service;


import hyzj.demo.Dao.AgentDao;
import hyzj.demo.Dao.MallUserDao;
import hyzj.demo.Dao.ProjectDao;
import hyzj.demo.Dao.RoleDao;
import hyzj.demo.Exception.DataLinkException;
import hyzj.demo.Exception.NoLoginException;
import hyzj.demo.ExceptionEnum.ExceptionEnum;
import hyzj.demo.Model.Agent;
import hyzj.demo.Model.MallUser;
import hyzj.demo.Model.Project;
import hyzj.demo.Model.Role;
import hyzj.demo.ModelVo.AgentVo;
import hyzj.demo.ModelVo.DiscountVo;
import hyzj.demo.ModelVo.UserVo;
import hyzj.demo.RsultModel.R_data;
import hyzj.demo.Utils.TimeUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AgentService {

    @Resource
    AgentDao agentDao;

    @Resource
    MallUserDao mallUserDao;
    @Resource
    RoleDao roleDao;

    @Resource
    ProjectDao projectDao;

    public List<UserVo> ObtainAgent() {

        List<UserVo> userVos = new ArrayList<>();
        List<MallUser> mallUsers = mallUserDao.loadByRid();
        for (MallUser mallUser : mallUsers) {
            UserVo userVo = new UserVo();
            userVo.setUser(mallUser);
            userVo.setRole(roleDao.loadByRid(mallUser.getR_id()));
            userVos.add(userVo);
        }
        return userVos;

    }

    @Transactional
    public UserVo updateAgent(String role, String mid, HttpSession session) {

        System.out.println("role+mid" + role + mid);


        boolean b = mallUserDao.updateRid(role, mid);
        UserVo userVo = new UserVo();
        UserVo userVo1 = (UserVo) session.getAttribute("UserVo");
        if (userVo1 == null){
            throw new NoLoginException(ExceptionEnum.NoLogin_Exception);
        }
//        String stationer = mid;
            System.out.println("开始修改用户或代理商");
        String stationer = userVo1.getUser().getM_id();
        if (b == true) {
            //在代理商表加入数据
            if (role.equals("4")) {
                //先判断是否存在该代理商
                if (agentDao.loadByMid(mid) == null) {
                    boolean a = agentDao.addAgent(mid, TimeUtils.getTime(), stationer);

                    if (a == true) {
                        MallUser mallUser = mallUserDao.loadById(mid);
                        userVo.setUser(mallUser);
                        userVo.setRole(roleDao.loadByRid(mallUser.getR_id()));
                    } else {
                        throw new DataLinkException(ExceptionEnum.DATALINK_Exception);
                    }
                }else {
                    agentDao.updateTime(TimeUtils.getTime());
                    MallUser mallUser = mallUserDao.loadById(mid);
                    userVo.setUser(mallUser);
                    userVo.setRole(roleDao.loadByRid(mallUser.getR_id()));
                }
            }
            if (role.equals("3")) {
                System.out.println(agentDao.loadByMid(mid));
                if (agentDao.loadByMid(mid) != null) {
                    boolean a = agentDao.deleteAgent(mid);

                    if (a == true) {
                        MallUser mallUser = mallUserDao.loadById(mid);
                        userVo.setUser(mallUser);
                        userVo.setRole(roleDao.loadByRid(mallUser.getR_id()));
                    } else {
                        throw new DataLinkException(ExceptionEnum.DATALINK_Exception);
                    }
                }else {
                    MallUser mallUser = mallUserDao.loadById(mid);
                    userVo.setUser(mallUser);
                    userVo.setRole(roleDao.loadByRid(mallUser.getR_id()));
                }
            }

        } else {
            throw new DataLinkException(ExceptionEnum.DATALINK_Exception);
        }
        return userVo;
    }

    /**
     * 显示下拉框折扣范围
     * @return
     */
    public List ShowDiscount() {

        Project projects = projectDao.ShowDiscount();
//        DiscountVo discountVo = new DiscountVo();
        List i = new ArrayList();
        double hi = projects.getDiscount_highest();
        double lo = projects.getDiscount_lowest();
        while (lo < hi){
//            System.out.println(lo);
            lo= lo+ 0.1 ;
            //保留下属点后一位
            BigDecimal b = new BigDecimal(lo);
            lo = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//             System.out.println(lo);
            i.add(lo);
        }
        return i;


    }

    public List<AgentVo> loadAgent() {

        List<Agent> agents = agentDao.loadAllAgent();

        List<AgentVo> agentVos = new ArrayList<>();

        for (Agent agent : agents){
            AgentVo agentVo = new AgentVo();
            agentVo.setAgent(agent);
            //谁被设置成代理商
            agentVo.setMallUser(mallUserDao.loadById(agent.getMid()));
            //谁设置的
            agentVo.setStationer(mallUserDao.loadById(agent.getStationer()));
            agentVos.add(agentVo);

        }
        return agentVos;


    }

    public AgentVo updateDiscount(String discount, String mid) {
        AgentVo agentVo = new AgentVo();
        if (agentDao.updateDiscount(discount,mid)){

            Agent agent = agentDao.loadByMid(mid);
            agentVo.setAgent(agent);
            //谁被设置成代理商
            agentVo.setMallUser(mallUserDao.loadById(agent.getMid()));
            //谁设置的
            agentVo.setStationer(mallUserDao.loadById(agent.getStationer()));
        }
        return agentVo;
    }
}
