package hyzj.demo.Dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ProjectInvestDao {

    @Select("select count(M_id) from mall_user where phone = #{0}")
    boolean checkPhone(@Param("0") String platphone);

    @Insert("insert into project_invest values(#{0},#{1},#{2},#{3},#{4},#{5},#{6},#{7})")
    boolean addInvester(@Param("0") String invest_id, @Param("1") String p_id,@Param("2") String project_id,@Param("3") int i,@Param("4") String name, @Param("5") int invest_amount, @Param("6") String addTime, @Param("7") int invest_stock);
}
