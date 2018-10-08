package hyzj.demo.Dao;

import hyzj.demo.Model.Project;
import org.apache.ibatis.annotations.Select;

public interface ProjectDao {

    @Select("select * from project where P_id = 'PmA1bP2PAVSUItWEZsLjeTTQAD1NFpktz'")
    Project ShowDiscount();
}
