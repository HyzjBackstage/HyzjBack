package hyzj.demo.Controller;

import hyzj.demo.Model.OrderAdd;
import hyzj.demo.Service.OrderAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("orderAdd")
public class OrderAddController {

    @Autowired
    OrderAddService orderAddService;

    @RequestMapping("/list")
    @ResponseBody
    public List<OrderAdd> loadList(){
        return orderAddService.loadList();
    }

//    params.add_record = $('#orderAdd_add_order').val();
//    params.single_people = $('#orderAdd_add_people').val();
//    params.single_phone = $('#orderAdd_add_phone').val();
//    params.c_id = $('#orderAdd_add_Cid').val();
//    params.number = $('#orderAdd_add_number').val();
//    params.price = $('#orderAdd_add_price').val();
//    params.add_time = $('#orderAdd_add_time').val();
//    params.add_describe = $('#orderAdd_add_describe').val();


    /**
     * 添加补录信息
     * @param single_people
     * @param single_phone
     * @param c_id
     * @param number
     * @param price
     * @param add_describe
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public boolean add(String single_people,String single_phone,String c_id,String number,
                       String price,String add_describe){
        return orderAddService.add(single_people,single_phone,c_id,number,price,add_describe);
    }


    /**
     * 更新补录信息
     * @param add_record
     * @param single_people
     * @param single_phone
     * @param c_id
     * @param number
     * @param price
     * @param add_describe
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public boolean update(String add_record,String single_people,String single_phone,String c_id,String number,
                          String price,String add_describe){
        return orderAddService.update(add_record,single_people,single_phone,c_id,number,price,add_describe);
    }


    @RequestMapping("/delete")
    @ResponseBody
    public boolean delete(String add_record){
        return orderAddService.delete(add_record);
    }
}
