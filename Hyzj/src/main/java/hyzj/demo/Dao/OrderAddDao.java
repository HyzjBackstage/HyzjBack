package hyzj.demo.Dao;

import hyzj.demo.Model.OrderAdd;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrderAddDao {

    /**
     * 查询补录信息
     * @return
     */
    @Select("select * from order_add")
    List<OrderAdd> loadList();

    /**
     * 插入补录信息
     * @param add_record
     * @param single_people
     * @param single_phone
     * @param c_id
     * @param number
     * @param price
     * @param add_time
     * @param add_describe
     * @return
     */
    @Insert("insert into order_add(Add_record,C_id,In_id,number,single_people,single_phone,add_time,add_describe," +
            "price) values(#{0},#{3},#{8},#{4},#{1},#{2},#{6},#{7},#{5})")
    boolean add(@Param("0") String add_record, @Param("1") String single_people,
                @Param("2") String single_phone, @Param("3") String c_id, @Param("4") String number,
                @Param("5") String price, @Param("6") String add_time, @Param("7") String add_describe,
                @Param("8") String In_id);


    /**
     * 更新补录信息
     * @param add_record
     * @param single_people
     * @param single_phone
     * @param c_id
     * @param number
     * @param price
     * @param add_time
     * @param add_describe
     * @return
     */
    @Update("update order_add set single_people = #{1},single_phone = #{2}," +
            "C_id = #{3},number = #{4},price = #{5},add_time = #{6},add_describe = #{7} where Add_record = #{0}")
    boolean update(@Param("0") String add_record, @Param("1") String single_people,
                   @Param("2") String single_phone, @Param("3") String c_id, @Param("4") String number,
                   @Param("5") String price, @Param("6") String add_time, @Param("7") String add_describe);

    /**
     * 删除补录信息
     * @param add_record
     * @return
     */
    @Delete("delete from order_add where Add_record = #{0}")
    boolean delete(@Param("0") String add_record);

    /**
     * 通过补录id获取数据
     * @param add_record
     * @return
     */
    @Select("select * from order_add where Add_record = #{0}")
    OrderAdd searchByAddRecord(@Param("0") String add_record);
}
