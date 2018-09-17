package hyzj.demo.Dao;

import hyzj.demo.Model.Year;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface YearDao {

    @Select("SELECT * from `year` ORDER BY `year`.yaer")
    List<Year> loadYear();


    @Insert("INSERT INTO `year` VALUES( #{Y_id}, #{year})")
    boolean addYear(@Param("Y_id")String Y_id,@Param("year")String year);

    @Delete("DELETE FROM `year` WHERE `year`.Y_id = #{Y_id}  ")
    boolean delete(@Param("Y_id") String y_id);

    @Update("UPDATE  `year` SET `year`.yaer = #{name} WHERE `year`.Y_id = #{Y_id};")
    boolean update(@Param("Y_id") String yid,@Param("name") String name);
}
