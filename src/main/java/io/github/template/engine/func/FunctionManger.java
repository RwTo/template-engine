package io.github.template.engine.func;


import java.util.HashMap;
import java.util.Map;

/**
 * @author renmw
 * @create 2024/5/12 21:07
 **/
public class FunctionManger {
    private final Map<String, TemplateFunc> funcMap = new HashMap<>();

    {
        DateTimeFormat dateTimeFormat = DateTimeFormat.getInstance();
        funcMap.put(dateTimeFormat.getFuncName(),dateTimeFormat);

        Now now = Now.getInstance();
        funcMap.put(now.getFuncName(),now);

        Hyperlink hyperlink = Hyperlink.getInstance();
        funcMap.put(hyperlink.getFuncName(),hyperlink);
    }


    public TemplateFunc getFunc(String funcName){
        return funcMap.get(funcName);
    }

    public void addFunc(String funcName, TemplateFunc func){
        this.funcMap.put(funcName,func);
    }
}
