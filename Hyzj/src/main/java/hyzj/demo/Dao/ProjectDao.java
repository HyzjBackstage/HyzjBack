package hyzj.demo.Dao;

import hyzj.demo.Model.Project;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ProjectDao {

    @Select("select * from project where project_id = 'PmA1bP2PAVSUItWEZsLjeTTQAD1NFpktz'")
    Project ShowDiscount();

    /**
     * 通过M_id查询project
     * @param m_id
     * @return
     */
    @Select("select * from project where project_id = #{0}")
    Project searchByMid(@Param("0") String m_id);
}
