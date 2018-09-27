package hyzj.demo.Exception;

import hyzj.demo.ExceptionEnum.ExceptionEnum;

public class NoUserException extends RuntimeException {
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public NoUserException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.code = code;
    }
}
