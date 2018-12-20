package hyzj.demo.Dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface IncomeYearDao {

    /**
     * 查询收入表中第一条数据，按时间排序，income_year表
     * @return
     */
    @Select("select income_year_id from income_year ORDER BY time DESC LIMIT 1")
    String searchIn_year_id();

    /**
     * 修改收入金额,income_year表
     * @param rebate_amount
     * @return
     */
    @Update("update income_year set amount_year = amount_year+#{0} ORDER BY time DESC LIMIT 1")
    boolean updateIncomeYear(@Param("0") String rebate_amount);

    /**
     * 插入商家返点数据  income_year表
     * @param income_year_id
     * @param rebate_time_year
     * @param rebate_amount
     * @return
     */
    @Insert("insert into income_year(income_year_id,time,amount_year) values(#{0},#{1},#{2})")
    boolean addIncomeYear(@Param("0") String income_year_id, @Param("1") String rebate_time_year, @Param("2") String rebate_amount);
}
