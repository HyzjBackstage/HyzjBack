package hyzj.demo.ModelVo;

import hyzj.demo.Model.Menu;

import java.util.List;

public class MenuVo {
    Menu menu;
    List<Menu> menus;

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "MenuVo{" +
                "menu=" + menu +
                ", menus=" + menus +
                '}';
    }
}
