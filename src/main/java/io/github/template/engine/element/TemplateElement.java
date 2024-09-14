package io.github.template.engine.element;

import io.github.template.engine.domain.ConvertSource;

/**
 * @author renmw
 * @create 2024/9/12 19:18
 **/
public interface TemplateElement {
    String getContent(ConvertSource convertSource);
}
