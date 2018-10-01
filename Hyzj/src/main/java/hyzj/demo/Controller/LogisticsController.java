package hyzj.demo.Controller;

import hyzj.demo.Model.orders;
import hyzj.demo.ModelVo.LogisticsVo;
import hyzj.demo.Service.LogisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("express")
public class LogisticsController {

    @Autowired
    LogisticsService LogisticsService;

    /**
     * 通过o_id获取物流信息Logistics,Logistics_company,mall_user,receiver,order表中的数据
     * @param o_id
     * @return
     */
    @RequestMapping("/SearchByOrder")
    @ResponseBody
    public LogisticsVo searchByOrder(String o_id){
        System.out.println("o_id:" + o_id);
        return LogisticsService.searchByOrder(o_id);
    }


    /**
     * 查询已完成定单
     * @return
     */
    @RequestMapping("/OrdersList")
    @ResponseBody
    public List<orders> ordersList() {
        return LogisticsService.ordersList();
    }

    /**
     * 保存编辑的订单信息
     * @return
     */
    @RequestMapping("/updateOrder")
    @ResponseBody
    public boolean orderDetail(String o_id,String lc_id,String status,String shipment_number){
        return LogisticsService.orderDetail(o_id,lc_id,status,shipment_number);
    }


}
