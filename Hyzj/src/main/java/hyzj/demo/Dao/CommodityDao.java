package hyzj.demo.Dao;

import hyzj.demo.Model.Commodity;
import hyzj.demo.ModelVo.CommodityVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CommodityDao {


    @Select("SELECT * FROM commodity")
    List<Commodity> loadCommodity();

    @Insert("insert into commodity(" +
            "C_id," +
            "Y_id," +
            "name," +
            "productionDate," +
            "shelfDate," +
            "image," +
            "price," +
            "comment," +
            "purchase_price," +
            "stock," +
            "addTime," +
            "state" +") values(#{0},#{2},#{1},#{7},#{6},#{8},#{4},'',#{5},#{3},#{9},#{10})")
    boolean add(@Param("0") String commodity,
                @Param("1") String addname,
                @Param("2") String year,
                @Param("3") String stock,
                @Param("4") float price,
                @Param("5") float purchase_price,
                @Param("6") String shelfDate,
                @Param("7") String productionDate,
                @Param("8") String detailphoto,
                @Param("9") String nowtime,
                @Param("10") int state);

    @Select("select * from commodity where C_id = #{0}")
    Commodity loadCommodityByid(@Param("0")String commodity);


    /**
     * 删除商品
     * @param cid
     * @return
     */
    @Delete("delete from commodity where C_id = #{0}")
    boolean deleteCommodity(@Param("0") String cid);

    /**
     * 更新
     * @param commodity
     * @param addname
     * @param year
     * @param stock
     * @param price
     * @param purchase_price
     * @param shelfDate
     * @param productionDate
     * @param editDetailphoto
     * @param state
     * @return
     */

    @Update("update commodity set " +
            "Y_id = #{2}," +
            "name = #{1}," +
            "productionDate = #{7}," +
            "shelfDate = #{6}," +
            "image = #{8}," +
            "price = #{4}," +
            "purchase_price = #{5}," +
            "stock = #{3}," +
            "state = #{9} " +
            " where C_id = #{0}")
    boolean update(@Param("0") String commodity,
                   @Param("1") String addname,
                   @Param("2") String year,
                   @Param("3") String stock,
                   @Param("4") float price,
                   @Param("5") float purchase_price,
                   @Param("6") String shelfDate,
                   @Param("7") String productionDate,
                   @Param("8") String editDetailphoto,
                   @Param("9") int state);
}
