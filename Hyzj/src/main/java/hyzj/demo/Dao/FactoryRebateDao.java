package hyzj.demo.Dao;

import hyzj.demo.Model.FactoryRebate;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface FactoryRebateDao {

    @Select("select * from factory_rebate")
    List<FactoryRebate> loadList();

    @Update("update factory_rebate set rebate_amount = #{1} where F_id = #{0}")
    boolean update(@Param("0") String F_id, @Param("1") String rebate_amount);

    /**
     * 添加记录
     * 商家返点只有项目发起人可以操作，项目id是珍酒商城id
     * @param f_id
     * @param rebate_time
     * @param rebate_amount
     * @return
     */
    @Insert("insert into factory_rebate values(#{0},#{1},#{2},#{3},#{4})")
    boolean add(@Param("0") String f_id,@Param("1") String user_id,@Param("2") String project_id, @Param("3") String rebate_time, @Param("4") String rebate_amount);

    /**
     * 删除
     * @param F_id
     * @return
     */
    @Delete("delete from factory_rebate where F_id = #{0}")
    boolean delete(@Param("0") String F_id);
}
