package hyzj.demo.Service;

import hyzj.demo.Dao.AgentDao;
import hyzj.demo.Dao.MallUserDao;
import hyzj.demo.Dao.PlatformUserDao;
import hyzj.demo.Dao.ProjectWalletDao;
import hyzj.demo.Exception.DataLinkException;
import hyzj.demo.Model.MallUser;
import hyzj.demo.Model.Platform_user;
import hyzj.demo.Utils.CodeUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.util.Password;

import javax.annotation.Resource;
import java.util.List;

import static hyzj.demo.ExceptionEnum.ExceptionEnum.DATALINK_Exception;

@Service
public class MallUserService {

    @Resource
    MallUserDao mallUserDao;

    @Resource
    AgentDao agentDao;

    @Resource
    ProjectWalletDao projectWalletDao;

    @Resource
    PlatformUserDao platformUserDao;

    /**
     * 显示所有用户
     *
     * @return
     */
    public List<MallUser> loadlist() {
        return mallUserDao.loadlist();
    }

    /**
     * 编辑用户信息
     *
     * @param m_id
     * @param name
     * @param id_card
     * @param phone
     * @param r_id
     * @return
     */
    @Transactional
    public boolean Update(String m_id, String name, String id_card, String phone, String r_id, String password) {
//        System.out.println("m_id:" + m_id + "id_card:" + id_card + "phone:" + phone + "r_id:" + r_id);
        return mallUserDao.Update(m_id, name, id_card, phone, r_id, password);
    }

    /**
     * 删除用户
     *
     * @param m_id
     * @return
     */
    @Transactional
    public boolean Delete(String m_id) {
        //要想删掉用户，先删掉购物车
        //然后删掉代理商
        System.out.println("service:delete" + m_id);
        try {
            MallUser mallUser = mallUserDao.loadById(m_id);
            if (agentDao.loadByMid(m_id) != null) {
                agentDao.deleteAgent(m_id);
            }
//            System.out.println("123456:"+mallUser.getSC_id());
            if (mallUserDao.Delete(m_id)) {
                return mallUserDao.deleteCartBySid(mallUser.getSC_id());
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new DataLinkException(e, DATALINK_Exception);
        }
    }

    /**
     * 添加用户
     *
     * @param r_id
     * @param name
     * @param id_card
     * @param phone
     * @return
     */
    @Transactional
    public MallUser Add(String r_id, String name, String id_card, String phone, String password) {

        CodeUtils codeUtils = new CodeUtils();
        //用户id
        String m_id = "M" + codeUtils.createRandom(false, 16);
        boolean b = mallUserDao.Add(m_id, r_id, name, id_card, phone, password);
        if (b == true) {
            //购物车id
            String Sid = "S" + codeUtils.createRandom(false, 12);
            boolean s = mallUserDao.addCart(Sid);
            if (s == true) {
                boolean m = mallUserDao.updateMallUserCart(Sid, m_id);
                if (m == true) {
                    MallUser mallUser = mallUserDao.loadById(m_id);
                    return mallUser;
                } else {
                    throw new DataLinkException(DATALINK_Exception);
                }

            } else {
                throw new DataLinkException(DATALINK_Exception);
            }
        } else {
            throw new DataLinkException(DATALINK_Exception);
        }

    }
}
