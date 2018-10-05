package hyzj.demo.Service;

import hyzj.demo.Dao.MenuDao;
import hyzj.demo.Dao.RoleDao;
import hyzj.demo.Dao.UserDao;
import hyzj.demo.Exception.DataLinkException;
import hyzj.demo.Exception.NoUserException;
import hyzj.demo.ExceptionEnum.ExceptionEnum;
import hyzj.demo.Model.MallUser;
import hyzj.demo.Model.Menu;
import hyzj.demo.Model.MenuRole;
import hyzj.demo.Model.Role;
import hyzj.demo.ModelVo.MenuVo;
import hyzj.demo.ModelVo.UserVo;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Resource
    UserDao userDao;

    @Resource
    MenuDao menuDao;

    @Resource
    RoleDao roleDao;

    @Transactional
    public UserVo login(HttpServletRequest request,HttpServletResponse response, HttpSession session) {
        try {
            String WXID = "" ;
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies){

                if (cookie.getName().equals("WXID")){
                    WXID = cookie.getValue();
                }
            }
//            String WXID = "xs02";
            System.out.println(WXID);

            MallUser mallUser = userDao.loadById(WXID);
            //用户不存在或者无效
            if (mallUser == null) {
                throw  new NoUserException(ExceptionEnum.NOUSER);
            }
            UserVo uvo = new UserVo();
            uvo.setUser(mallUser);
            //核对密码
            // if (mallUser.get().equals(pwd)) {
            //获取菜单（权限）信息
            List<Menu> menus = menuDao.loadByid(mallUser.getR_id());
            //获取个人权限名
            Role role = roleDao.loadByRid(mallUser.getR_id());
            uvo.setRole(role);
//            System.out.println("全部菜单："+menus);
            List<MenuVo> menuVos = new ArrayList<>();
            //一级菜单
            List<Menu> FirstMenu = new ArrayList<>();
            for (Menu menu : menus) {
                if (menu.getMenu_sort().equals("0")) {
                    FirstMenu.add(menu);
                }
            }

//            System.out.println("FirstMenu" + FirstMenu);

            for (Menu firstMenu : FirstMenu){
                MenuVo menuVo = new MenuVo();
                menuVo.setMenu(firstMenu);
                List<Menu> menuList = new ArrayList<>();
                for (Menu sortMenu : menus){

                    if (firstMenu.getParents().equals(sortMenu.getParents()) && Integer.parseInt(sortMenu.getMenu_sort()) != 0){
                        menuList.add(sortMenu);
                    }

                }
                menuVo.setMenus(menuList);
                menuVos.add(menuVo);
            }

            uvo.setMenuVos(menuVos);
            session.setAttribute("MallUser", uvo);
            //  }
            return uvo;
        } catch (Exception e) {
            throw new DataLinkException(e, ExceptionEnum.DATALINK_Exception);
        }


    }

    public String WindowLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session, String phone, String pwd) {
        //查找该用户
        MallUser mallUser = userDao.loadByPhone(phone);
        if (mallUser == null) {
            //4没有该用户
            return "4";
        }
        if (mallUser.getR_id().equals("3")){
            //该用户没有权限
            return "3";
        }
        UserVo uvo = new UserVo();
        uvo.setUser(mallUser);
        //核对密码
        if (mallUser.getPassword() !=null) {
            if (mallUser.getPassword().equals(pwd)) {
                //获取菜单（权限）信息
                List<Menu> menus = menuDao.loadByid(mallUser.getR_id());
                //获取个人权限名
                Role role = roleDao.loadByRid(mallUser.getR_id());
                uvo.setRole(role);
//            System.out.println("全部菜单："+menus);
                List<MenuVo> menuVos = new ArrayList<>();
                //一级菜单
                List<Menu> FirstMenu = new ArrayList<>();
                for (Menu menu : menus) {
                    if (menu.getMenu_sort().equals("0")) {
                        FirstMenu.add(menu);
                    }
                }

//            System.out.println("FirstMenu" + FirstMenu);

                for (Menu firstMenu : FirstMenu) {
                    MenuVo menuVo = new MenuVo();
                    menuVo.setMenu(firstMenu);
                    List<Menu> menuList = new ArrayList<>();
                    for (Menu sortMenu : menus) {

                        if (firstMenu.getParents().equals(sortMenu.getParents()) && Integer.parseInt(sortMenu.getMenu_sort()) != 0) {
                            menuList.add(sortMenu);
                        }

                    }
                    menuVo.setMenus(menuList);
                    menuVos.add(menuVo);
                }

                uvo.setMenuVos(menuVos);
                System.out.println(uvo);
                session.setAttribute("UserVo", uvo);
                //成功
                return "1";
            } else {
                //密码错误
                return "2";
            }
        }
        else {
            return "4";
        }
    }
}
