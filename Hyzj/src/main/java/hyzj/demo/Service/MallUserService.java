package hyzj.demo.Service;

import hyzj.demo.Dao.MallUserDao;
import hyzj.demo.Exception.DataLinkException;
import hyzj.demo.Model.MallUser;
import org.springframework.stereotype.Service;
import sun.security.util.Password;

import javax.annotation.Resource;
import java.util.List;

import static hyzj.demo.ExceptionEnum.ExceptionEnum.DATALINK_Exception;

@Service
public class MallUserService {

    @Resource
    MallUserDao mallUserDao;

    /**
     * 显示所有用户
     * @return
     */
    public List<MallUser> loadlist(){
        return mallUserDao.loadlist();
    }

    /**
     * 编辑用户信息
     * @param m_id
     * @param name
     * @param id_card
     * @param phone
     * @param r_id
     * @return
     */
    public boolean Update(String m_id, String name, String id_card, String phone, String r_id,String password) {
        System.out.println("m_id:"+m_id+"id_card:"+id_card+"phone:"+phone+"r_id:"+r_id);
        return mallUserDao.Update(m_id,name,id_card,phone,r_id,password);
    }

    /**
     * 删除用户
     * @param m_id
     * @return
     */
    public boolean Delete(String m_id) {
        System.out.println("service:delete"+m_id);
        try {
            return mallUserDao.Delete(m_id);
        }catch (Exception e){
            throw new DataLinkException(e,DATALINK_Exception);
        }
    }

    /**
     * 添加用户
     *
     * @param m_id
     * @param r_id
     * @param name
     * @param id_card
     * @param phone
     * @return
     */
    public boolean Add(String m_id, String r_id, String name, String id_card, String phone,String password) {
        return mallUserDao.Add(m_id,r_id,name,id_card,phone,password);
    }
}
