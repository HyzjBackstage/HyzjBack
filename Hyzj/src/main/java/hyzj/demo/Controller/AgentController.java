package hyzj.demo.Controller;

import hyzj.demo.ExceptionEnum.ExceptionEnum;
import hyzj.demo.Model.Agent;
import hyzj.demo.Model.Project;
import hyzj.demo.ModelVo.AgentVo;
import hyzj.demo.ModelVo.DiscountVo;
import hyzj.demo.ModelVo.UserVo;
import hyzj.demo.RsultModel.R_data;
import hyzj.demo.Service.AgentService;
import hyzj.demo.Utils.ResultUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("agent")
public class AgentController {

    @Autowired
    AgentService agentService;

    /**
     * 获取是用户还是代理商
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    @ResponseBody
    public R_data<List<Agent>> loadAgent()throws Exception{

        System.out.println(agentService.ObtainAgent());
        return ResultUtils.success(agentService.ObtainAgent(),ExceptionEnum.SUCCESS);
    }

    /**
     * 添加代理商
     * @param role
     * @param mid
     * @param session
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public R_data<UserVo> updateAgent(String role, String mid, HttpSession session){

       /* role = "4";
        mid = "M8anxivd8p76745ks";*/

        return ResultUtils.success(agentService.updateAgent(role,mid,session),ExceptionEnum.SUCCESS);
    }

    /**
     * 展示可选择的折扣
     * @return
     */
    @RequestMapping("/ShowDiscount")
    @ResponseBody
    public R_data<List> ShowDiscount(){
        System.out.println(agentService.ShowDiscount());
        return ResultUtils.success(agentService.ShowDiscount(),ExceptionEnum.SUCCESS);
    }

    /**
     * 只获取代理商
     */
    @RequestMapping("/load")
    @ResponseBody
    public R_data<List<AgentVo>> load(){
        return ResultUtils.success(agentService.loadAgent(),ExceptionEnum.SUCCESS) ;
    }

    @RequestMapping("/updateDiscount")
    @ResponseBody
    public R_data<List<AgentVo>> updateDiscount(String discount,String mid){
       return ResultUtils.success(agentService.updateDiscount(discount,mid),ExceptionEnum.SUCCESS);
    }


}
