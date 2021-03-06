package hyzj.demo.ExceptionController;


import hyzj.demo.Exception.*;
import hyzj.demo.ExceptionEnum.ExceptionEnum;
import hyzj.demo.RsultModel.R_data;
import hyzj.demo.Utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionController {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R_data ExceptionAuthority(Exception e) {


        if (e instanceof NoAuthorityException){
            NoAuthorityException noAuthorityException = (NoAuthorityException) e;
            logger.info("报错原因={}",noAuthorityException.getMessage());
            return ResultUtils.error(null,noAuthorityException.getCode(),noAuthorityException.getMessage());
        }
        if (e instanceof NoLoginException){
            NoLoginException noLoginException = (NoLoginException) e;
            logger.info("报错原因={}",noLoginException.getMessage());
            return ResultUtils.error(null,noLoginException.getCode(),noLoginException.getMessage());
        }
        if (e instanceof DataLinkException){
            DataLinkException dataLinkException = (DataLinkException) e;
            logger.info("报错原因={}",dataLinkException.getMessage());
            return ResultUtils.error(null,dataLinkException.getCode(),dataLinkException.getMessage());
        }
        if (e instanceof FileException){ FileException fileException = (FileException) e;
            logger.info("报错原因={}",fileException.getMessage());
            return ResultUtils.error(null,fileException.getCode(),fileException.getMessage());
        }
        if (e instanceof NoUserException){ NoUserException noUserException = (NoUserException) e;
            logger.info("报错原因={}",noUserException.getMessage());
            return ResultUtils.error(null,noUserException.getCode(),noUserException.getMessage());
        }
        else {
            logger.info("【系统异常】={}",e);
            return ResultUtils.error(null,ExceptionEnum.UNKNOW_Exception);
        }
    }
}
