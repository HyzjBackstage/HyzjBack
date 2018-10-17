package hyzj.demo.Controller;

import hyzj.demo.Model.FactoryRebate;
import hyzj.demo.Service.FactoryRebateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("businessReturn")
public class FactoryRebateContrroller {

    @Autowired
    FactoryRebateService factoryRebateService;

    @RequestMapping("/list")
    @ResponseBody
    public List<FactoryRebate> loadList(){
        return factoryRebateService.ladList();
    }

    @RequestMapping("/add")
    @ResponseBody
    public boolean add(String rebate_amount){
        return factoryRebateService.add(rebate_amount);
    }

    @RequestMapping("/update")
    @ResponseBody
    public boolean update(String f_id,String rebate_amount){
        return factoryRebateService.update(f_id,rebate_amount);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public boolean delete(String f_id){
        System.out.println("f_id:" + f_id);

        return factoryRebateService.delete(f_id);
    }
}
