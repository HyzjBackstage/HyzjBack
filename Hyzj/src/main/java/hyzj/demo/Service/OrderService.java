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

    @Resource
    CouponDao couponDao;
    /**
     * 获取订单数据
     * @return
     */


    public List<OrderVo> loadList() {

        System.out.println("orderVO::::");
        List<OrderVo> orderVo = new ArrayList<>();
        try{

            List<orders> ordersList = orderDao.loadList();

            for (orders order: ordersList) {
                OrderVo orderVo1 = new OrderVo();
                orderVo1.setT_orders(order);
                orderVo1.setT_mallUser(mallUserDao.loadById(order.getM_id()));
                orderVo1.setT_receiver(receiverDao.loadById(order.getRe_id()));

                if (order.getOFid()!="" && order.getOFid()!=null) {
                    String coid = offeCouponDao.loadById(order.getOFid()).getCOid();
                    orderVo1.setCoupon(couponDao.loadByid2(coid));
                }
                orderVo.add(orderVo1);
            }

            return orderVo;
        }catch (Exception e){
            e.printStackTrace();
        }
        return orderVo;
    }


    /**
     * 通过时间查询订单
     * @param time_start
     * @param time_end
     * @return
     */
    public List<OrderVo> searchListByTime(String time_start, String time_end) {
        List<OrderVo> orderVo = new ArrayList<>();
        try{

            List<orders> orders = orderDao.searchListByTime(time_start,time_end);

            for (hyzj.demo.Model.orders order: orders) {
                OrderVo orderVo2 = new OrderVo();
                orderVo2.setT_orders(order);
                orderVo2.setT_mallUser(mallUserDao.loadById(order.getM_id()));
                orderVo2.setT_receiver(receiverDao.loadById(order.getRe_id()));
                if (order.getOFid()!="" && order.getOFid()!=null) {
                    String coid = offeCouponDao.loadById(order.getOFid()).getCOid();
                    orderVo2.setCoupon(couponDao.loadByid2(coid));
                }

                orderVo.add(orderVo2);
            }

            return orderVo;
        }catch (Exception e){
            e.printStackTrace();
        }
        return orderVo;
    }

    /**
     * 通过订单编号查询订单
     * @param o_id
     * @return
     */
    public OrderVo searchById(String o_id) {
        OrderVo orderVo = new OrderVo();
        try{
            orders order = orderDao.loadById(o_id);
            if (order == null){
                return new OrderVo();
            }


            orderVo.setT_orders(order);
            orderVo.setT_receiver(receiverDao.loadById(order.getRe_id()));
            orderVo.setT_mallUser(mallUserDao.loadById(order.getM_id()));
            if (order.getOFid()!="" && order.getOFid()!=null) {
                String coid = offeCouponDao.loadById(order.getOFid()).getCOid();
                orderVo.setCoupon(couponDao.loadByid2(coid));
            }

            return orderVo;
        }catch (Exception e){
            e.printStackTrace();
        }
        return orderVo;
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
