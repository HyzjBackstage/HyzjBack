package hyzj.demo.Filter;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        boolean ss = requestURI.contains("**/index**");
        System.out.println("8080:"+ss);
        //访问除login.jsp（登录页面）和验证码servlet之外的jsp/servlet都要进行验证
        if (    requestURI.contains("/")
                && !requestURI.contains("404.html")
                && "/index.html".contains(requestURI)
                ) {
            //判断cookies中是否有用户信息，如果没有则重定向到登录页面
            String WXID = "" ;
            Cookie[] cookies = req.getCookies();
            if (cookies == null){
                res.sendRedirect( "404.html");
                return;
            }
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("WXID")) {
                    WXID=cookie.getValue();
                }
            }
            System.out.println("wxid:"+WXID);
            if ( WXID == null || WXID.equals("") ) {
                res.sendRedirect( "404.html");
                return;
            }


        }
        //继续访问其他资源
        chain.doFilter(req, res);
    }

    public void destroy() {

    }
}
