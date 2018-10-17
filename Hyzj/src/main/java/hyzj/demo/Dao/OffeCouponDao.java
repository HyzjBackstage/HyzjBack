package hyzj.demo.Dao;

import hyzj.demo.Model.offeCoupon;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface OffeCouponDao {

    @Select("select * from offeCoupon where OFid = #{0}")
    offeCoupon loadById(@Param("0") String OFid);
}
