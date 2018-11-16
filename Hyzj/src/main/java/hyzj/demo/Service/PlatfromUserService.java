package hyzj.demo.Service;

import hyzj.demo.Dao.*;
import hyzj.demo.Model.MallUser;
import hyzj.demo.Model.Platform_user;
import hyzj.demo.Utils.TimeUtils;
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

    @Resource
    ProjectInvestDao projectInvestDao;

    @Resource
    ProjectWalletDao projectWalletDao;

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
    public boolean addPlatToMall(String phone,String password,String p_id,String nickname,
                                 String name,String id_card,int invest_amount,int invest_stock) {
        String Platphone = phone;
        boolean checkPhone_1 = mallUserDao.checkPhone(Platphone);
        String addTime = TimeUtils.getTime();
        String SC_id = "000";
        String invest_id = "000";
        String wallet_id = "000";
        String project_id = "PmA1bP2PAVSUItWEZsLjeTTQAD1NFpktz";    //珍酒商城项目id
//        int invest_amount = 10000;      //添加投资金额
//        int invest_stock = 1;

        String wallet_amount = "0";     //初始钱包金额


        /**
         *  完成三个操作：
         *  1、添加投资人到商城，包括已经在商城注册过的人，或者没在商城注册过的人
         *  2、添加投资人到投资人表
         *  3、给每个投资人都要添加一个钱包，初始值为零
         *
         *  问题：这样写的话，投资人表和钱包表可以重复添加
         *  解决办法：P_id 使用p开头，M_id使用M开头，每次添加投资人是检测id首字母。
         */

        try{

            if (checkPhone_1 == false){

                System.out.println("该手机号没有在商城注册过");
                //给新用户生成购物车id，r_id默认为投资人
                SC_id = "SC" + addTime + phone;
                //生成投资表id
                invest_id = "Inv" + addTime + p_id + phone;
                //生成项目钱包表id
                wallet_id = "Wal" + addTime + p_id + phone;
                boolean shopCart = shoppingCartDao.insertNewCart(SC_id);
                boolean platform = platformUserDao.addPlatToMall(phone,password,p_id,nickname,name,id_card,SC_id,addTime);

                boolean Invester = projectInvestDao.addInvester(invest_id,p_id,project_id,1,name,invest_amount,addTime,invest_stock);
                boolean InvesterWallet = projectWalletDao.addInvesterWallet(wallet_id,p_id,project_id,wallet_amount);


                if (shopCart&&platform&&Invester&&InvesterWallet == true){
                    return true;
                }else {
                    return false;
                }

            }else{

                System.out.println("该手机号已注册过（mall_user表中已有该手机号）");

                //生成投资表id
                invest_id = "Inv" + addTime + p_id + phone;
                //生成项目钱包表id
                wallet_id = "Wal" + addTime + p_id + phone;

                MallUser malluser = mallUserDao.loadByPhone(Platphone);
                SC_id = malluser.getSC_id();
                boolean b1 = platformUserDao.addPlatToMall(phone,password,p_id,nickname,name,id_card,SC_id,addTime);
                boolean b2 = mallUserDao.logOutMallUser(Platphone);

                boolean Invester = projectInvestDao.addInvester(invest_id, p_id, project_id, 1, name, invest_amount, addTime, invest_stock);
                boolean InvesterWallet = projectWalletDao.addInvesterWallet(wallet_id,p_id,project_id,wallet_amount);


                if (b1&&b2&&Invester&&InvesterWallet == true){
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
     * 多次添加同一手机号，返回错误信息
     * @param platPhone
     * @return
     */
    public boolean checkPhone(String platPhone) {
        System.out.println("checkPhone:" + mallUserDao.checkPhone(platPhone));

        return mallUserDao.checkPhone(platPhone);
    }
}
