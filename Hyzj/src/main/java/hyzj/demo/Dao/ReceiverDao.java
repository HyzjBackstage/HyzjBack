package hyzj.demo.Dao;

import hyzj.demo.Model.receiver;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ReceiverDao {

    /**
     * 通过收货人id查询收货信息
     * @param re_id
     * @return
     */
    @Select("select * from receiver where Re_id = #{0}")
    receiver loadById(@Param("0") String re_id);


}
