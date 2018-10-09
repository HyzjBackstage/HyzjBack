package hyzj.demo.Service;

import hyzj.demo.Dao.MallUserDao;
import hyzj.demo.Dao.PlatformUserDao;
import hyzj.demo.Dao.ShoppingCartDao;
import hyzj.demo.Model.MallUser;
import hyzj.demo.Model.Platform_user;
import hyzj.demo.Utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PlatfromUserService {

    @Resource
    PlatformUserDao platformUserDao;

    @Resource
    MallUserDao mallUserDao;

    @Resource
    ShoppingCartDao shoppingCartDao;


    /**
     * 通过电话查询平台表用户信息
     * @param phone
     * @return
     */
    public Platform_user selectByPhone(String phone) {
        System.out.println("platfrom_user:" + platformUserDao.selectByPhone(phone));

        return platformUserDao.selectByPhone(phone);
    }

    /**
     * 将平台用户信息添加进商城用户表（拉取投资人信息）
     * @param phone
     * @param password
     * @param p_id
     * @param nickname
     * @param name
     * @param id_card
     * @return
     */
    public boolean addPlatToMall(String phone,String password,String p_id,String nickname,String name,String id_card) {
        String Platphone = phone;
        Boolean checkPhone_1 = mallUserDao.checkPhone(Platphone);
        String addTime = TimeUtils.getTime();
        String SC_id = "000";

        try{
            if (checkPhone_1 == false){

                System.out.println("该手机号没有在商城注册过");
                //给新用户生成购物车id，r_id默认为投资人
                SC_id = "SC" + addTime + phone;
                boolean shopCart = shoppingCartDao.insertNewCart(SC_id);
                boolean platform = platformUserDao.addPlatToMall(phone,password,p_id,nickname,name,id_card,SC_id,addTime);

                if (shopCart&&platform == true){
                    return true;
                }else {
                    return false;
                }

            }else{

                System.out.println("该手机号已注册过（mall_user表中已有该手机号）");

                MallUser malluser = mallUserDao.loadByPhone(Platphone);
                SC_id = malluser.getSC_id();
                boolean b1 = platformUserDao.addPlatToMall(phone,password,p_id,nickname,name,id_card,SC_id,addTime);
                boolean b2 = mallUserDao.logOutMallUser(Platphone);

                if (b1&&b2 == true){
                    return true;
                }else {
                    return false;
                }
            }

        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }

    /**
     * 检测mall_user表中是否有相同手机号
     * @param platPhone
     * @return
     */
    public boolean checkPhone(String platPhone) {
        System.out.println("checkPhone:" + mallUserDao.checkPhone(platPhone));

        return mallUserDao.checkPhone(platPhone);
    }
}
