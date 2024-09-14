package io.github.template.engine.annotation;

import java.lang.annotation.*;

/**
 * @author renmw
 * @create 2024/9/14 22:20
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TemplateModel {
    String value();
}
