package hyzj.demo.Dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface IncomeMonthDao {

    /**
     * 查询收入表中第一条数据，按时间排序，income_month表
     * @return
     */
    @Select("select income_month_id from income_month ORDER BY time DESC LIMIT 1")
    String searchIn_month_id();

    /**
     * 修改收入金额,income_month表
     * @param rebate_amount
     * @return
     */
    @Update("update income_month set amount_month = amount_month+#{0} ORDER BY time DESC LIMIT 1")
    boolean updateIncomeMonth(@Param("0") String rebate_amount);

    /**
     * 插入商家返点数据  income_month表
     * @param income_month_id
     * @param rebate_time_month
     * @param rebate_amount
     * @return
     */
    @Insert("insert into income_month(income_month_id,time,amount_month) values(#{0},#{1},#{2})")
    boolean addIncomeMonth(@Param("0") String income_month_id, @Param("1") String rebate_time_month, @Param("2") String rebate_amount);
}
