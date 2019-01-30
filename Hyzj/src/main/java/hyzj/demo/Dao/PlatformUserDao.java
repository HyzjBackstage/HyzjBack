package hyzj.demo.Dao;

import hyzj.demo.Model.Platform_user;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface PlatformUserDao {

    /**
     * 通过手机号查询
     * @param phone
     * @return
     */
    @Select("select * from platform_user where phone = #{0}")
    Platform_user selectByPhone(@Param("0") String phone);

    /**
     * 将平台用户数据插入到商城
     * @param phone
     * @param password
     * @param p_id
     * @param nickname
     * @param name
     * @param id_card
     * @param SC_id
     * @param addTime
     * @return
     */
    @Insert("insert into mall_user values(#{2},'2',#{6},#{0},#{3},#{5},#{4},'',#{1},#{7})")
    boolean addPlatToMall(@Param("0") String phone, @Param("1") String password, @Param("2") String p_id,
                          @Param("3") String nickname, @Param("4") String name, @Param("5") String id_card,
                          @Param("6") String SC_id, @Param("7") String addTime);

    @Update("update platform_user set nickname = #{0} where P_id = #{1}")
    boolean updateNickname(@Param("0") String nickname,@Param("1") String pid);
}
