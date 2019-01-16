package hyzj.demo.Service;

import hyzj.demo.Dao.*;
import hyzj.demo.Model.orders;
import hyzj.demo.ModelVo.OrderVo;
import hyzj.demo.Utils.DatatablesView;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Resource
    OrderDao orderDao;

    @Resource
    MallUserDao mallUserDao;

    @Resource
    ReceiverDao receiverDao;

    @Resource
    OffeCouponDao offeCouponDao;

    /**
     * 获取订单数据
     * @return
     */
//    public DatatablesView<OrderVo> loadList() {
//
//        System.out.println("orderVO::::");
//
//        try{
//            List<OrderVo> orderVo = new ArrayList<>();
//            List<orders> orders = orderDao.loadList();
//
//            for (hyzj.demo.Model.orders order: orders) {
//                OrderVo orderVo1 = new OrderVo();
//                orderVo1.setT_orders(order);
//                orderVo1.setT_mallUser(mallUserDao.loadById(order.getM_id()));
//                orderVo1.setT_receiver(receiverDao.loadById(order.getRe_id()));
//                orderVo1.setOffecoupon(offeCouponDao.loadById(order.getOFid()));
//
////                System.out.println("mallUserDao:" + mallUserDao.loadById(order.getM_id()));
////                System.out.println("receiverDao:" + receiverDao.loadById(order.getRe_id()));
//
//                orderVo.add(orderVo1);
//            }
//
//            int count = orderDao.orderCount();
//            DatatablesView<OrderVo> dataView = new DatatablesView<OrderVo>();
//            dataView.setRecordsTotal(count);
//            dataView.setData(orderVo);
//
//            return dataView;
//        }catch (Exception e){
//            throw new RuntimeException(e.getMessage());
//        }
//    }

    public List<OrderVo> loadList() {

        System.out.println("orderVO::::");

        try{
            List<OrderVo> orderVo = new ArrayList<>();
            List<orders> orders = orderDao.loadList();

            for (hyzj.demo.Model.orders order: orders) {
                OrderVo orderVo1 = new OrderVo();
                orderVo1.setT_orders(order);
                orderVo1.setT_mallUser(mallUserDao.loadById(order.getM_id()));
                orderVo1.setT_receiver(receiverDao.loadById(order.getRe_id()));
                orderVo1.setOffecoupon(offeCouponDao.loadById(order.getOFid()));
//                if (order.getOFid() != null){
//                    orderVo1.setOffecoupon(offeCouponDao.loadById(order.getOFid()));
//                }else{
//                    orderVo1.setOffecoupon();
//                }


//                System.out.println("mallUserDao:" + mallUserDao.loadById(order.getM_id()));
//                System.out.println("receiverDao:" + receiverDao.loadById(order.getRe_id()));

                orderVo.add(orderVo1);
            }

            return orderVo;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }


    /**
     * 通过时间查询订单
     * @param time_start
     * @param time_end
     * @return
     */
    public List<OrderVo> searchListByTime(String time_start, String time_end) {
        try{
            List<OrderVo> orderVo = new ArrayList<>();
            List<orders> orders = orderDao.searchListByTime(time_start,time_end);

            for (hyzj.demo.Model.orders order: orders) {
                OrderVo orderVo2 = new OrderVo();
                orderVo2.setT_orders(order);
                orderVo2.setT_mallUser(mallUserDao.loadById(order.getM_id()));
                orderVo2.setT_receiver(receiverDao.loadById(order.getRe_id()));
                orderVo2.setOffecoupon(offeCouponDao.loadById(order.getOFid()));

                orderVo.add(orderVo2);
            }

            return orderVo;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }

    /**
     * 通过订单编号查询订单
     * @param o_id
     * @return
     */
    public OrderVo searchById(String o_id) {

        try{
            orders order = orderDao.loadById(o_id);
            if (order == null){
                return new OrderVo();
            }

            OrderVo orderVo = new OrderVo();
            orderVo.setT_orders(order);
            orderVo.setT_receiver(receiverDao.loadById(order.getRe_id()));
            orderVo.setT_mallUser(mallUserDao.loadById(order.getM_id()));
            orderVo.setOffecoupon(offeCouponDao.loadById(order.getOFid()));

            return orderVo;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 通过订单号删除订单
     * @param o_id
     * @return
     */
    public boolean delete(String o_id) {
        return orderDao.delete(o_id);
    }
}
