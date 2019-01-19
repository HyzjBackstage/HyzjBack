package hyzj.demo.Dao;

import hyzj.demo.Model.logistics;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface LogisticsDao {

    @Select("select * from logistics where O_id = #{0}")
    logistics loadByOrder(@Param("0") String o_id);

    @Update("update logistics set LC_id = #{1},shipment_number = #{2} where o_id = #{0}")
    boolean orderDetail(@Param("0") String o_id, @Param("1") String lc_id, @Param("2") String shipment_number);

    @Insert("insert into logistics value(#{0},#{1},#{2},#{3},#{4})")
    boolean insertDetail(@Param("0") String lid,@Param("1") String lcid,@Param("2") String shipment_number,
                         @Param("3") String amount,@Param("4") String oid );
}
