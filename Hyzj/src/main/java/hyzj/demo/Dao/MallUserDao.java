package hyzj.demo.Dao;


import hyzj.demo.Model.MallUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface MallUserDao {

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from mall_user")
    List<MallUser> loadlist();

    /**
     * 通过用户id查询用户信息
     * @param m_id
     * @return
     */
    @Select("select * from mall_user where M_id = #{0}")
    MallUser loadById(@Param("0") String m_id);

    /**
     * 编辑用户信息
     * @param m_id
     * @param name
     * @param id_card
     * @param phone
     * @param r_id
     * @return
     */
    @Update("update mall_user set name = #{1},ID_card = #{2},phone = #{3},r_id = #{4},password = #{5} where M_id = #{0}")
    boolean Update(@Param("0") String m_id, @Param("1") String name, @Param("2") String id_card, @Param("3") String phone, @Param("4") String r_id,@Param("5") String password);

    /**
     * 删除用户
     * @param m_id
     * @return
     */
    @Delete("delete from mall_user where M_id = #{0}")
    boolean Delete(@Param("0") String m_id);

    /**
     * 添加用户
     * @param m_id
     * @param r_id
     * @param name
     * @param id_card
     * @param phone
     * @return
     */
    @Insert("insert into mall_user(M_id,R_id,name,ID_card,phone,nickname,password) values(#{0},#{1},#{2},#{3},#{4},#{4},#{5})")
    boolean Add(@Param("0") String m_id, @Param("1") String r_id, @Param("2") String name, @Param("3") String id_card, @Param("4") String phone,@Param("5") String password);
}
