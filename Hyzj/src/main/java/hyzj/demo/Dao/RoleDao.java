package hyzj.demo.Dao;

import hyzj.demo.Model.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface RoleDao {

    @Select("SELECT * FROM role WHERE R_id = #{0}")
    Role loadByRid(@Param("0") String rid);
}
