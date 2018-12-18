package hyzj.demo.Filter;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.geom.FlatteningPathIterator;
import java.io.IOException;
import java.net.URLDecoder;

@Aspect
@Component
public class LoginFilter implements Filter {



    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        //如果没有登录
//        String requestURI = req.getRequestURI().substring(req.getRequestURI().indexOf("/", 1), req.getRequestURI().length());
        String requestURI = req.getServletPath();
        System.out.println("requestURI=" + requestURI);
////        boolean ss = requestURI.contains("**/index**");
//        System.out.println("80:"+requestURI);
        //访问除login.jsp（登录页面）和验证码servlet之外的jsp/servlet都要进行验证
        if (
                requestURI.contains("/mallback/issue")
                && requestURI.contains("/mallback")
                && !requestURI.contains("/404.html")
                && !requestURI.contains("/login.html")
                && !requestURI.contains("/MallUserLogin")
                && !requestURI.contains("/Hyzj")
                && !requestURI.contains("/js")
                && !requestURI.contains("/css")
                && !requestURI.contains("/fonts")
                && !requestURI.contains("/images")
                && !requestURI.contains("/custom-js")
                && !requestURI.contains("/plugins")
                && !requestURI.contains("/goods")
                && !requestURI.contains("/issue")
//                && !requestURI.contains("/save")
//                && !requestURI.contains("/index")
//                && !requestURI.contains("/print")
                ) {
            //判断cookies中是否有用户信息，如果没有则重定向到登录页面
            String MID = "" ;
            Cookie[] cookies = req.getCookies();
            HttpSession session = req.getSession();
            if (cookies == null && session.getAttribute("UserVo") == null ){
                res.sendRedirect( req.getContextPath()+"/login.html");
                return;
            }
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("MID")) {
                        MID = cookie.getValue();
                    }
                }
            }

            System.out.println("MID:"+MID);
            if ( MID == null || MID.equals("") && session.getAttribute("UserVo") == null) {
                res.sendRedirect( req.getContextPath()+"/login.html");
                return;
            }


        }

        chain.doFilter(request, response);

    }

    public void destroy() {

    }




}
