package hyzj.demo.Dao;

import hyzj.demo.ModelVo.StockVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface StockDao {

    //获取全部的商品库存
    @Select("SELECT * from StockVo")
    List<StockVo> loadStock();

    @Update("UPDATE StockVo SET stock = #{1} WHERE C_id = #{0}")
    boolean update(@Param("0") String cid, @Param("1") String stock);

    @Insert("insert into modifuedStock(Cid,unmodifiedNumble,modifiedNumble,reTime,Mid) " +
            "values(#{0},#{2},#{1},#{4},#{3}) ")
    boolean addModifuedStock(
                             @Param("0") String cid,
                             @Param("1") String stock,
                             @Param("2") String unmodifiedNumble,
                             @Param("3") String mid,
                             @Param("4") String reTime);
}
