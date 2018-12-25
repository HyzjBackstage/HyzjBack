package hyzj.demo.Dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface IncomeDayDao {

    /**
     * 插入商家返点数据
     * @param income_day_id
     * @param rebate_time_day
     * @param rebate_amount
     * @return
     */
    @Insert("insert into income_day(income_day_id,time,amount_day) values(#{0},#{1},#{2})")
    boolean addIncomeDay(@Param("0") String income_day_id, @Param("1") String rebate_time_day, @Param("2") String rebate_amount);

    /**
     * 查询收入表中第一条数据，按时间排序
     * @return
     */
    @Select("select income_day_id from income_day ORDER BY time DESC LIMIT 1")
    String searchIn_day_id();

    /**
     * 修改收入金额
     * @param rebate_amount
     * @return
     */
    @Update("update income_day set amount_day = amount_day+#{0} ORDER BY time DESC LIMIT 1")
    boolean updateIncomeDay(@Param("0") String rebate_amount);
}
