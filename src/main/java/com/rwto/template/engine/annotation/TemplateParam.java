package com.rwto.template.engine.annotation;

import java.lang.annotation.*;

/**
 * @author renmw
 * @create 2024/5/5 10:50
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TemplateParam {
    String value();
    String desc() default "";
}
