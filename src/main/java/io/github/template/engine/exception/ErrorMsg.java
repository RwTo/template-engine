package io.github.template.engine.exception;

/**
 * @author renmw
 * @create 2024/5/5 10:29
 **/
public enum ErrorMsg {
    PARAMETER_ILLEGAL("parameter illegality"),
    ;

    private String errorMsg;

    ErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
