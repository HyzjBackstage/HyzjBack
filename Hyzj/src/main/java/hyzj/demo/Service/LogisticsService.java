package hyzj.demo.Service;

import hyzj.demo.Dao.*;
import hyzj.demo.Model.logistics;
import hyzj.demo.Model.orders;
import hyzj.demo.ModelVo.LogisticsVo;
import hyzj.demo.Utils.CodeUtils;
import org.springframework.expression.spel.ast.BooleanLiteral;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LogisticsService {

    @Resource
    LogisticsDao logisticsDao;

    @Resource
    LogisticsCompanyDao logisticsCompanyDao;

    @Resource
    MallUserDao mallUserDao;

    @Resource
    ReceiverDao receiverDao;

    @Resource
    OrderDao orderDao;


    /**
     * 通过订单id查询物流信息
     * @param o_id
     * @return
     */
    public LogisticsVo searchByOrder(String o_id) {

//        System.out.println("order:"+ order);
//        System.out.println("logistic:"+ logistic);
        try{
            LogisticsVo logisticsVo = new LogisticsVo();
            orders order = orderDao.loadById(o_id);


            logistics logistic = logisticsDao.loadByOrder(o_id);

            //订单存在
            if (logistic == null){
                System.out.println("logistics为空！！！");
                logisticsVo.setT_logistics_company(null);

            }else {
                logisticsVo.setT_logistics_company(logisticsCompanyDao.loadById(logistic.getLC_id()));
            }


            logisticsVo.setT_logistics(logisticsDao.loadByOrder(o_id));

            logisticsVo.setT_mallUser(mallUserDao.loadById(order.getM_id()));
            logisticsVo.setT_orders(orderDao.loadById(order.getO_id()));
            logisticsVo.setT_receiver(receiverDao.loadById(order.getRe_id()));

//            System.out.println("logisticsVo::" + logisticsVo);

            return logisticsVo;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 查询已完成订单
     * @return
     */
    public List<orders> ordersList() {

//        System.out.println("ordersList:" + orderDao.ordersList());

        return orderDao.ordersList();
    }

    /**
     * 保存编辑的订单信息
     * @param o_id
     * @param lc_id
     * @param status
     * @param shipment_number
     * @return
     */
    public boolean orderDetail(String o_id, String lc_id, String status, String shipment_number) {

        try {
            logistics logistics1 = logisticsDao.loadByOrder(o_id);
            CodeUtils codeUtils = new CodeUtils();
            if (logistics1 != null) {
                boolean logistics = logisticsDao.orderDetail(o_id, lc_id, shipment_number);
                boolean order = orderDao.updateDetail(o_id, status);

                if (logistics && order) {
                    return true;
                }
            }else {
                String amount = logisticsCompanyDao.loadById(lc_id).getAmount();
                boolean logistics =  logisticsDao.insertDetail("L"+codeUtils.createRandom(true,12),lc_id,shipment_number,amount,o_id);
                boolean order = orderDao.updateDetail(o_id, status);
                if (logistics && order) {
                    return true;
                }
            }
            return false;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
