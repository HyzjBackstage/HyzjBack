package hyzj.demo.Dao;

import hyzj.demo.Model.T_girl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

public interface TestDao {

    @Select("select * from user")
    List<T_girl> loadGirl();

}
