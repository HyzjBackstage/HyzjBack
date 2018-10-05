package hyzj.demo.Dao;

import hyzj.demo.Model.MallUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserDao {

    @Select("select * from mall_user where WX_id = #{0} ")
    MallUser loadById(@Param("0") String username);


    @Select("select * from mall_user where phone = #{0}")
    MallUser loadByPhone(@Param("0") String phone);
}
