package io.github.template.engine.exception;



/**
 * @author renmw
 * @create 2024/5/5 10:26
 **/
public class TemplateException extends RuntimeException{
    private String msg;

    public TemplateException(String errorMsg) {
        this.msg = errorMsg;
    }

    public static TemplateException build(ErrorMsg msg){
        return new TemplateException(msg.getErrorMsg());
    }
}
