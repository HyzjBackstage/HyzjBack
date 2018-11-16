package hyzj.demo.Dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ProjectWalletDao {

    @Select("select count(M_id) from mall_user where phone = #{0}")
    boolean checkPhone(@Param("0") String platphone);

    @Insert("insert into project_wallet values(#{0},#{1},#{2},#{3})")
    boolean addInvesterWallet(@Param("0") String wallet_id,@Param("1")String p_id, @Param("2") String project_id, @Param("3") String wallet_amount);
}
