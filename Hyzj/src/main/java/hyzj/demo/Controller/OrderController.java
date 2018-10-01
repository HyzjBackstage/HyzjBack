package hyzj.demo.Controller;

import hyzj.demo.ModelVo.OrderVo;
import hyzj.demo.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService orderService;

//    private String O_id;            //订单编号
//    private String Re_id;           //收货人id
//    private String M_id;            //商城用户id（发货人）
//    private String Co_id;           //优惠券id
//    private String L_id;            //快递公司
//    private String price;           //总价
//    private String order_time;      //下单时间
//    private String self_lifting;    //是否自提
//    private String status;          //订单状态


    @RequestMapping("/list")
    @ResponseBody
    public List<OrderVo> loadList(){
        System.out.println("order list:" + orderService.loadList());

        return orderService.loadList();
    }


    @RequestMapping("/seachByTime")
    @ResponseBody
    public List<OrderVo> searchListByTime(String time_start,String time_end){
        System.out.println("start:" + time_start);
        System.out.println("end:" + time_end);

        return orderService.searchListByTime(time_start,time_end);
    }

    @RequestMapping("/seachById")
    @ResponseBody
    public OrderVo searchById(String o_id){
        System.out.println("search by o_id" + o_id);
        return orderService.searchById(o_id);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public boolean delete(String o_id){
        System.out.println("delete" + o_id);
        return orderService.delete(o_id);
    }

}
