package hyzj.demo.Dao;

import hyzj.demo.Model.orders;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrderDao {

    /**
     * 显示所有订单
     * @return
     */
    @Select("select * from orders")
    List<orders> loadList();

    /**
     * 按时间查询
     * @param time_start
     * @param time_end
     * @return
     */
    @Select("select * from orders where order_time < #{1} and order_time > #{0}")
    List<orders> searchListByTime(@Param("0") String time_start, @Param("1") String time_end);

    /**
     * 按订单号查询
     * @param o_id
     * @return
     */
    @Select("select * from orders where O_id = #{0}")
    orders loadById(@Param("0") String o_id);

    /**
     * 删除订单
     * @param o_id
     * @return
     */
    @Delete("delete from orders where O_id = #{0}")
    boolean delete(@Param("0") String o_id);

    /**
     * 查询已完成的订单
     * @return
     */
    @Select("select * from orders where status = '1' or status = '2' or status = '3' or status = '4' or status = '5'")
    List<orders> ordersList();

    /**
     * 更新订单状态
     * @param o_id
     * @param status
     * @return
     */
    @Update("update orders set status = #{1} where O_id = #{0}")
    boolean updateDetail(@Param("0") String o_id, @Param("1") String status);
}
