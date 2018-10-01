package hyzj.demo.Dao;

import hyzj.demo.Model.logistics_company;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface LogisticsCompanyDao {

    @Select("select * from logistics_company where LC_id = #{0}")
    logistics_company loadById(@Param("0") String lc_id);


}
