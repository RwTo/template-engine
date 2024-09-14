package io.github.template.engine.element;

import io.github.template.engine.domain.ConvertSource;
import io.github.template.engine.utils.TemplateUtils;


/**
 * @author renmw
 * @create 2024/9/13 13:44
 **/
public class ContentElement implements TemplateElement{
    private final String content;

    public ContentElement(String content) {
        this.content = content;
    }

    @Override
    public String getContent(ConvertSource convertSource) {
        return TemplateUtils.parseAndConvert(content, convertSource.getParams(), convertSource.getRowIdx(), convertSource.getFunctionManger());
    }
}
