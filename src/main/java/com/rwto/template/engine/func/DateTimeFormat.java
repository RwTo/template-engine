package com.rwto.template.engine.func;


import com.rwto.template.engine.constant.TemplateConstant;
import com.rwto.template.engine.exception.ErrorMsg;
import com.rwto.template.engine.exception.TemplateException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author renmw
 * @create 2024/5/5 10:17
 **/
public class DateTimeFormat extends AbstractTemplateFunc {
    private final DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(TemplateConstant.DEFAULT_PATTERN);

    private DateTimeFormat(String name) {
        super(name);
    }

    @Override
    public String execute(Object... args) {
        if(args.length != 2 && args.length != 1){
            throw TemplateException.build(ErrorMsg.PARAMETER_ILLEGAL);
        }
        String dateTime = String.valueOf(args[0]);
        LocalDateTime arg0 = LocalDateTime.parse(dateTime, DEFAULT_DATE_TIME_FORMATTER);
        DateTimeFormatter dateTimeFormatter = args.length == 1 ? DEFAULT_DATE_TIME_FORMATTER : DateTimeFormatter.ofPattern(String.valueOf(args[1]));;
        return arg0.format(dateTimeFormatter);
    }

    public static DateTimeFormat getInstance() {
        return DateTimeFormat.DateTimeFormatHolder.INSTANCE;
    }

    private static class DateTimeFormatHolder{
        private static final DateTimeFormat INSTANCE = new DateTimeFormat("DateTimeFormat");
    }
}
