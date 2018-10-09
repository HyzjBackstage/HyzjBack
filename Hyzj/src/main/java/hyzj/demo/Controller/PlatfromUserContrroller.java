package hyzj.demo.Controller;

import hyzj.demo.Model.Platform_user;
import hyzj.demo.Service.PlatfromUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("platformUser")
@Controller
public class PlatfromUserContrroller {

    @Autowired
    PlatfromUserService platfromUserService;

    /**
     * 通过电话查询平台表用户信息
     * @param phone
     * @return
     */
    @RequestMapping("/searchByPhone")
    @ResponseBody
    public Platform_user selectByPhone(String phone){
        return platfromUserService.selectByPhone(phone);
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
    @RequestMapping("/addUser")
    @ResponseBody
    public boolean addPlatToMall(String phone,String password,String p_id,String nickname,String name,String id_card){
        return platfromUserService.addPlatToMall(phone,password,p_id,nickname,name,id_card);

    }

    /**
     * 检测mall_user表中是否有相同手机号
     * @param platPhone
     * @return
     */
    @RequestMapping("/checkPhone")
    public boolean checkPhone(String platPhone){
        return platfromUserService.checkPhone(platPhone);
    }


}
