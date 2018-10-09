package hyzj.demo.Dao;

import org.apache.ibatis.annotations.Insert;

public interface ShoppingCartDao {

    @Insert("insert into shopping_cart values(#{0})")
    boolean insertNewCart(String SC_id);
}
