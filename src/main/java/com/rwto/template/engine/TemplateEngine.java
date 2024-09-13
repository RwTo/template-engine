package com.rwto.template.engine;


import com.rwto.template.engine.exception.ErrorMsg;
import com.rwto.template.engine.exception.TemplateException;
import com.rwto.template.engine.parse.TemplateParser;

import java.util.List;

/**
 * @author renmw
 * @create 2024/5/12 15:04
 **/
public class TemplateEngine {

    private final TemplateParser parser;

    private TemplateEngine(TemplateParser parser) {
        this.parser = parser;
    }

    public static TemplateEngineBuilder builder(){
        return new TemplateEngineBuilder();
    }

    public String parseAndConvert(String tmpl, List<Object> objs){
        return this.parseTemplate(tmpl).convert(objs);
    }
    /**
     * 解析模板
     * @param tmpl
     */
    public TemplateEngine parseTemplate(String tmpl){
        /*解析参数 List fields*/
        /*解析函数*/
        parser.parse(tmpl);
        return this;
    }

    /**
     * 转换
     * @param objs
     * @return
     */
    public String convert(List<Object> objs){
        return parser.convert(objs);
    }


    public static class TemplateEngineBuilder{
        private TemplateParser parser;

        public TemplateEngineBuilder setParser(TemplateParser parser){
            this.parser = parser;
            return this;
        }

        public TemplateEngine build(){
            if(null == parser){
                throw TemplateException.build(ErrorMsg.NEED_PARSER);
            }
            return new TemplateEngine(parser);
        }
    }
}
