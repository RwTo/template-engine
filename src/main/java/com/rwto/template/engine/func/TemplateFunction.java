package com.rwto.template.engine.func;

import java.util.List;
import java.util.Map;

/**
 * @author renmw
 * @create 2024/5/12 16:14
 **/
public class TemplateFunction {
    private final String name;
    private final List<String> argFields;
    private final TemplateFunc func;

    public TemplateFunction(String name, List<String> argFields, TemplateFunc func) {
        this.name = name;
        this.argFields = argFields;
        this.func = func;
    }

    private String doFunc(Map<String,Object> params){
        Object[] args = new String[this.argFields.size()];
        int i = 0;
        for (String argsField : this.argFields) {
            args[i++] = params.get(argsField);
        }
        return this.func.execute(args);
    }
}
