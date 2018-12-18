package hyzj.demo.Controller;

import hyzj.demo.ModelVo.OrderVo;
import hyzj.demo.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService orderService;

//    @RequestMapping("/list")
//    @ResponseBody
//    public String loadList(HttpServletRequest request){
//        System.out.println("order list:" + orderService.loadList());
//
//        DatatablesView<OrderVo> dataTable = orderService.loadList();
//        dataTable.setDraw(1);
//        String data = JSON.toJSONString(dataTable);
//
//        System.out.println("json data:" + data);
//
//        return data;
//    }

    @RequestMapping("/list")
    @ResponseBody
    public List<OrderVo> loadList(HttpServletRequest request){
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
