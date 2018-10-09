package hyzj.demo.Dao;

import hyzj.demo.Model.CouponType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CouponTypeDao {

    @Select("select * from coupon")
    List<CouponType> getAllCouponType();

}
