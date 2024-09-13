package com.rwto.template.engine.parse;


import com.rwto.template.engine.func.FunctionManger;

/**
 * @author renmw
 * @create 2024/5/12 21:34
 **/
public abstract class AbstractTemplateParser implements TemplateParser {
    protected final FunctionManger funcManger;

    protected AbstractTemplateParser(FunctionManger funcManger) {
        this.funcManger = funcManger;
    }
}
