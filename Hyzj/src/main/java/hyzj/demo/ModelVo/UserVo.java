package hyzj.demo.ModelVo;

import hyzj.demo.Model.MallUser;
import hyzj.demo.Model.Menu;
import hyzj.demo.Model.Role;

import java.util.List;

public class UserVo {
    private MallUser user;
    private List<MenuVo> menuVos;
    private Role role;
    public MallUser getUser() {
        return user;
    }

    public void setUser(MallUser user) {
        this.user = user;
    }

    public List<MenuVo> getMenuVos() {
        return menuVos;
    }

    public void setMenuVos(List<MenuVo> menuVos) {
        this.menuVos = menuVos;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "user=" + user +
                ", menuVos=" + menuVos +
                ", role=" + role +
                '}';
    }
}

