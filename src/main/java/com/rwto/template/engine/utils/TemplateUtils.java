package com.rwto.template.engine.utils;


import com.rwto.template.engine.annotation.TemplateParam;
import com.rwto.template.engine.constant.TemplateConstant;
import com.rwto.template.engine.func.FunctionManger;
import com.rwto.template.engine.func.TemplateFunc;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author renmw
 * @create 2024/5/12 23:48
 **/
public class TemplateUtils {

    /**
     * 解析字段
     * @param tmpl
     * @return
     */
    public static Map<String, String> parseFields(String tmpl) {
        Map<String, String> map = new HashMap<>();
        Pattern pattern = Pattern.compile(TemplateConstant.REGEX_FIELD);
        Matcher matcher = pattern.matcher(tmpl);
        while (matcher.find()){
            map.put(matcher.group(),matcher.group(1));
        }
        return map;
    }


    public static String convertByFunc(String content, FunctionManger functionManger) {

        Pattern pattern = Pattern.compile(TemplateConstant.REGEX_FUNC);
        Matcher matcher1;
        do{
            Matcher matcher = pattern.matcher(content);
            while(matcher.find()){
                String function = matcher.group();
                String funcName = matcher.group(1);
                String funcArgs = matcher.group(2);
                String[] args = new String[0];
                if(StringUtils.isNotEmpty(funcArgs)){
                    args = funcArgs.trim().split(",");
                }
                TemplateFunc func = functionManger.getFunc(funcName);
                if(null == func){
                    //方法不存在，不替换
                    continue;
                }
                content = content.replace(function,func.execute(args));
            }

            matcher1 = pattern.matcher(content);
        }while (matcher1.find());

        return content;
    }


    public static Map<String, Object> parseParams(List<Object> objs) {
        Map<String, Object> ans = new HashMap<>();
        for (Object obj : objs) {
            parseParamObj(obj,ans);
        }
        return ans;
    }

    private static void parseParamObj(Object obj, Map<String, Object> ans) {
        Class<?> clz = obj.getClass();

        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields) {
            TemplateParam param = field.getAnnotation(TemplateParam.class);
            if(null == param){
                continue;
            }
            try {
                field.setAccessible(true);
                ans.put(param.value(), field.get(obj));
            } catch (IllegalAccessException e) {
            }
        }
    }
}
