package hyzj.demo.Controller;


import hyzj.demo.Model.MallUser;
import hyzj.demo.Service.MallUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("malluser")
public class MallUserController {

    @Autowired
    MallUserService mallUserService;

    /**
     * 显示所有用户
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<MallUser> loadList(){
        return mallUserService.loadlist();
    }

    /**
     * 添加用户
     * @param m_id
     * @param r_id
     * @param name
     * @param id_card
     * @param phone
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public boolean Add(String m_id,String r_id,String name,String id_card,String phone,String password){
        return mallUserService.Add(m_id,r_id,name,id_card,phone,password);
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
    @RequestMapping("/update")
    @ResponseBody
    public boolean Update(String m_id,String name,String id_card,String phone,String r_id,String password){
        return mallUserService.Update(m_id,name,id_card,phone,r_id,password);
    }

    /**
     * 删除用户
     * @param m_id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public boolean Delete(String m_id){
        System.out.println("delete");
        return mallUserService.Delete(m_id);
    }

}
