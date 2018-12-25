package hyzj.demo.Dao;

import hyzj.demo.Model.Income;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface IncomeDao {

    /**
     * 补录收入插入income表中
     * @param in_id
     * @param m_id
     * @param income_amount
     * @param unallocated_amount
     * @return
     */
    @Insert("insert into income values(#{0},#{1},#{2},#{3},#{4})")
    boolean add(@Param("0") String in_id, @Param("1") String m_id, @Param("2") String add_time, @Param("3") String income_amount, @Param("4") String unallocated_amount);

    /**
     * 通过in_id获取记录
     * @param in_id
     * @return
     */
    @Select("select * from income where In_id = #{0}")
    Income searchByInid(@Param("0") String in_id);

    /**
     * 更新income表记录
     * @param in_id
     * @param m_id
     * @param add_time
     * @param income_amount
     * @param unallocated_amount
     * @return
     */
    @Update("update income set M_id = #{1},time = #{2},income_amount = #{3},unallocated_amount = #{4} where In_id = #{0}")
    boolean update(@Param("0") String in_id, @Param("1") String m_id, @Param("2") String add_time, @Param("3") String income_amount, @Param("4") String unallocated_amount);
}
