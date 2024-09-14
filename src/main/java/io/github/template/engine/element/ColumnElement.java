package io.github.template.engine.element;

import io.github.template.engine.domain.ConvertSource;

/**
 * @author renmw
 * @create 2024/9/13 12:06
 **/
public class ColumnElement implements TemplateElement{
    private final String prefix;
    private final ContentElement content;
    private final String suffix;


    public ColumnElement(String prefix, ContentElement content, String suffix) {
        this.prefix = prefix;
        this.content = content;
        this.suffix = suffix;
    }

    @Override
    public String getContent(ConvertSource convertSource) {
        return this.prefix + content.getContent(convertSource) + this.suffix;
    }
}
