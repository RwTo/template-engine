package com.rwto.template.engine.func;


import com.rwto.template.engine.constant.TemplateConstant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author renmw
 * @create 2024/5/5 10:17
 **/
public class Now extends AbstractTemplateFunc {
    private final DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(TemplateConstant.DEFAULT_PATTERN);

    private Now(String funcName) {
        super(funcName);
    }

    @Override
    public String execute(Object... args) {
        DateTimeFormatter dateTimeFormatter = args.length == 1 ? DateTimeFormatter.ofPattern(String.valueOf(args[0])) : DEFAULT_DATE_TIME_FORMATTER;;
        return LocalDateTime.now().format(dateTimeFormatter);
    }

    public static Now getInstance(){
        return Now.NowHolder.INSTANCE;
    }

    public static class NowHolder{
        private static final Now INSTANCE = new Now("Now");
    }
}
