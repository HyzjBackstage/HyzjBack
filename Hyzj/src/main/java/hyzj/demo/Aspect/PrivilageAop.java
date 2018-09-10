package hyzj.demo.Aspect;

import hyzj.demo.Anotation.Privilege;
import hyzj.demo.Exception.NoAuthorityException;
import hyzj.demo.ExceptionEnum.ExceptionEnum;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;


@Aspect
@Component
public class PrivilageAop {
    //打印日志
    private static Logger logger = LoggerFactory.getLogger(PrivilageAop.class);
    //计时
    long nowSec = 0;

    //切面方法
    @Pointcut("@annotation(hyzj.demo.Anotation.Privilege)))")
    public void ControllerMethod() {
    }

    //在方法执行之前
    @Before("ControllerMethod()")
    public void dobefore(JoinPoint joinPoint) throws Exception {

        /**
         * 打印切面的信息
         * JoinPoint可以访问连接点的细节（方法名、参数）
         */
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("url={}", request.getRequestURI());
        logger.info("method={}", request.getMethod());
        logger.info("id={}", request.getRemoteAddr());
        //获取方法名
        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        //获取参数
        logger.info("args={}", joinPoint.getArgs());

        //获取所需权限
        String menu_name = "";

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(Privilege.class)) {
            //得到方法上的注解
            Privilege privilege = method.getAnnotation(Privilege.class);
            //得到注解中的name值
            menu_name = privilege.value();
        }
        System.out.println("权限名称：" + menu_name);
        logger.info("权限名称={}", menu_name);

        if (!"查找全部女生".equals(menu_name)) {
            //抛出没有
            throw new NoAuthorityException(ExceptionEnum.NoAuthority_Exception);
        }

    }

    //之后
    @AfterReturning("ControllerMethod()")
    public void doAfter() {
        System.out.println("结束....");
        System.out.println("耗时" + (System.currentTimeMillis() - nowSec) + "毫秒");
    }

    //抛出异常
    @AfterThrowing("ControllerMethod()")
    public void doAfterThrowing() {

        System.out.println("出错了");
    }

    //运行
    @Around("ControllerMethod()")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("- - - - - - - - - - - - - - ");
        System.out.println("开始....");
        Object object = pjp.proceed();//执行该方法
        System.out.println("成功....");
        return object;
    }
}
