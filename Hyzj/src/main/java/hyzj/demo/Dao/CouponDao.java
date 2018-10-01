package hyzj.demo.Dao;

import com.sun.tracing.dtrace.ProviderAttributes;
import hyzj.demo.Model.Coupon;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

public interface CouponDao {

    /**
     * 折扣优惠
     *
     * @return
     */
    @Select("SELECT * FROM coupon WHERE type = '1'")
    List<Coupon> loadDiscount();

    @Insert("insert into coupon values(#{0},'',#{1},#{2},#{3},#{4},'1')")
    boolean addDiscount(@Param("0") String cid,
                        @Param("1") String discount,
                        @Param("2") String strat,
                        @Param("3") String end,
                        @Param("4") String condition);

    @Delete("DELETE FROM coupon WHERE coupon.Co_id = #{0}")
    boolean deleteDiscount(@Param("0") String coid);


    @Update("UPDATE coupon SET condition_use = #{4}," +
            "discount = #{1} ,  " +
            "starting_time = #{2} , " +
            "end_time = #{3}  " +
            "WHERE Co_id = #{0}")
    boolean updateDiscount(@Param("0") String cid,
                           @Param("1") String discount,
                           @Param("2") String strat,
                           @Param("3") String end,
                           @Param("4") String condition);

//------------------------------------------------------------

    /**
     * 金额优惠
     *
     * @return
     */
    @Select("SELECT * FROM coupon WHERE type = '2'")
    List<Coupon> loadAmount();

    @Insert("insert into coupon values(#{0},#{1},'',#{2},#{3},#{4},'2')")
    boolean addAmount(@Param("0") String cid,
                      @Param("1") String amount,
                      @Param("2") String strat,
                      @Param("3") String end,
                      @Param("4") String condition);

    @Update("UPDATE coupon SET condition_use = #{4}," +
            "amount = #{1} ,  " +
            "starting_time = #{2} , " +
            "end_time = #{3}  " +
            "WHERE Co_id = #{0}")
    boolean updateAmount(@Param("0") String cid,
                         @Param("1") String amount,
                         @Param("2") String strat,
                         @Param("3") String end,
                         @Param("4") String condition);

    //删除
    @Delete("DELETE FROM coupon WHERE coupon.Co_id = #{0}")
    boolean deleteAmount(@Param("0") String coid);

    /**
     * 通过co_id获取优惠券信息
     * @param Co_id
     * @return
     */
    @Select("select * from coupon where Co_id = #{0}")
    Coupon loadById(@Param("0") String Co_id);
}
