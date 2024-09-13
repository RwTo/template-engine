package io.github.template.engine.parse;

import java.util.List;

/**
 * @author renmw
 * @create 2024/5/12 15:34
 **/
public interface TemplateParser {

    void parse(String tmpl);

    String convert(List<Object> objs);
}
