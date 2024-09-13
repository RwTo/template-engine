package io.github.template.engine.func;


/**
 * @author renmw
 * @create 2024/5/5 10:17
 **/
public abstract class AbstractTemplateFunc implements TemplateFunc {
    public final String funcName;

    public AbstractTemplateFunc(String funcName){
        this.funcName = funcName;
    }

    public String getFuncName(){
        return funcName;
    }
}
