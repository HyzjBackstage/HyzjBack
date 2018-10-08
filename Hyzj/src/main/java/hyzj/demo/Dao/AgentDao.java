package hyzj.demo.Dao;

import hyzj.demo.Model.Agent;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface AgentDao {

    @Insert("insert into agent(Mid,addTime,stationer) values(#{0},#{1},#{2})")
    boolean addAgent(@Param("0") String mid, @Param("1") String time,@Param("2") String stationer);


    @Delete("delete from agent where Mid = #{0} ")
    boolean deleteAgent( @Param("0") String mid);

    @Select("select * from agent where Mid = #{0}")
    Agent loadByMid(@Param("0") String mid);

    @Update("update agent set addTime = #{0} ")
    boolean updateTime(@Param("0") String time);

    @Select("select * from agent")
    List<Agent> loadAllAgent();

    @Update("update agent set discount = #{0} where Mid = #{1}")
    boolean updateDiscount(@Param("0") String discount, @Param("1") String mid);
}
