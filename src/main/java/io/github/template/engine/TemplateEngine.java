package io.github.template.engine;


import io.github.template.engine.domain.ConvertSource;
import io.github.template.engine.element.TemplateElement;
import io.github.template.engine.func.FunctionManger;
import io.github.template.engine.func.TemplateFunc;
import io.github.template.engine.utils.TemplateUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author renmw
 * @create 2024/5/12 15:04
 **/
public class TemplateEngine {

    private List<TemplateElement> templateElements;
    private FunctionManger functionManger;

    private TemplateEngine(FunctionManger functionManger) {
        this.functionManger = functionManger;
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
        this.templateElements = TemplateUtils.parseTemplate(tmpl);
        return this;
    }

    /**
     * 转换
     * @param objs
     * @return
     */
    public String convert(List<Object> objs){
        Map<String, List<Object>> params = TemplateUtils.parseParams(objs);
        ConvertSource convertSource = ConvertSource.builder()
                .functionManger(this.functionManger)
                .params(params)
                .build();
        StringBuilder content = new StringBuilder();
        for (TemplateElement element : this.templateElements) {
            content.append(element.getContent(convertSource));
        }
        return content.toString();
    }


    public void reset(){
        this.templateElements = null;
    }

    /**
     * 建造者
     */
    public static class TemplateEngineBuilder{
        private final FunctionManger functionManger = new FunctionManger();

        public TemplateEngineBuilder addFunction(String funcName, TemplateFunc func){
            this.functionManger.addFunc(funcName,func);
            return this;
        }

        public TemplateEngine build(){
            return new TemplateEngine(functionManger);
        }
    }
}
