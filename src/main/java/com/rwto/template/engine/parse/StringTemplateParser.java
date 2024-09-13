package com.rwto.template.engine.parse;


import com.rwto.template.engine.func.FunctionManger;
import com.rwto.template.engine.utils.TemplateUtils;

import java.util.List;
import java.util.Map;

/**
 * @author renmw
 * @create 2024/5/12 15:34
 **/
public class StringTemplateParser extends AbstractTemplateParser {

    String tmpl;
    /*${name} -> name*/
    Map<String, String> fields;

    public StringTemplateParser(FunctionManger funcManger) {
        super(funcManger);
    }

    @Override
    public void parse(String tmpl) {
        this.tmpl = tmpl;
        /*解析字段*/
        this.fields = TemplateUtils.parseFields(tmpl);
    }

    @Override
    public String convert(List<Object> objs) {
        /*解析参数 Map field -> value*/
        Map<String, Object> params = TemplateUtils.parseParams(objs);

        String content = tmpl;
        /*参数替换*/
        for (Map.Entry<String, String> entry : fields.entrySet()) {
            content = content.replace(entry.getKey(), String.valueOf(params.get(entry.getValue())));
        }
        /*函数处理*/
        return TemplateUtils.convertByFunc(content,this.funcManger);
    }
}
