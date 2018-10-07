package hyzj.demo.Service;

import hyzj.demo.Dao.OrderAddDao;
import hyzj.demo.Model.order_add;
import hyzj.demo.Utils.TimeUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderAddService {

    @Resource
    OrderAddDao orderAddDao;

    /**
     * 获取补录记录
     * @return
     */
    public List<order_add> loadList() {

        System.out.println("补录表记录：" + orderAddDao.loadList());
        return orderAddDao.loadList();
    }

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
    public boolean add(String single_people,String single_phone,String c_id,
                       String number,String price,String add_describe) {

        try{
            String add_record = "AR" + TimeUtils.getTime() + c_id;

            String add_time = TimeUtils.getTime();
            System.out.println("add_time:" + add_time);
            return orderAddDao.add(add_record,single_people,single_phone,c_id,number,price,add_time,add_describe);

        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
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
    public boolean update(String add_record, String single_people, String single_phone, String c_id, String number, String price, String add_describe) {

        String add_time = TimeUtils.getTime();

        return orderAddDao.update(add_record,single_people,single_phone,c_id,number,price,add_time,add_describe);
    }


    /**
     * 删除补录信息
     * @param add_record
     * @return
     */
    public boolean delete(String add_record) {
        return orderAddDao.delete(add_record);
    }
}
