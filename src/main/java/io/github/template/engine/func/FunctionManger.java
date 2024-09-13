package io.github.template.engine.func;


import java.util.HashMap;
import java.util.Map;

/**
 * @author renmw
 * @create 2024/5/12 21:07
 **/
public class FunctionManger {
    private final Map<String, TemplateFunc> funcMap;

    private FunctionManger(Map<String, TemplateFunc> funcMap) {
        this.funcMap = funcMap;
    }


    public TemplateFunc getFunc(String funcName){
        return funcMap.get(funcName);
    }

    public static FunctionMangerBuilder builder(){
         return new FunctionMangerBuilder();
    }

    public static class FunctionMangerBuilder{

        private final Map<String, TemplateFunc> funcMap = new HashMap<>();

        {
            DateTimeFormat dateTimeFormat = DateTimeFormat.getInstance();
            funcMap.put(dateTimeFormat.getFuncName(),dateTimeFormat);

            Now now = Now.getInstance();
            funcMap.put(now.getFuncName(),now);

            Hyperlink hyperlink = Hyperlink.getInstance();
            funcMap.put(hyperlink.getFuncName(),hyperlink);
        }


        public FunctionMangerBuilder addFunc(String funcName, TemplateFunc func){
            funcMap.put(funcName,func);
            return this;
        }

        public FunctionManger build(){
            return new FunctionManger(funcMap);
        }
    }
}
