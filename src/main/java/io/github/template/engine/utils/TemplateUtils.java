package io.github.template.engine.utils;


import io.github.template.engine.annotation.TemplateModel;
import io.github.template.engine.annotation.TemplateParam;
import io.github.template.engine.constant.TemplateConstant;
import io.github.template.engine.element.*;
import io.github.template.engine.func.FunctionManger;
import io.github.template.engine.func.TemplateFunc;

import java.lang.reflect.Field;
import java.util.*;
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


    public static Map<String, List<Object>> parseParams(List<Object> objs) {
        Map<String, List<Object>> ans = new HashMap<>();
        for (Object obj : objs) {
            if(obj instanceof Collection){
                Collection<?> list = (Collection<?>)obj;
                for (Object o : list) {
                    parseParamObj(o, ans);
                }
            }else{
                parseParamObj(obj, ans);
            }
        }
        return ans;
    }

    private static void parseParamObj(Object obj, Map<String, List<Object>> ans) {
        Class<?> clz = obj.getClass();
        TemplateModel model = clz.getAnnotation(TemplateModel.class);
        String prefix = null == model ? "" : model.value();
        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields) {
            TemplateParam param = field.getAnnotation(TemplateParam.class);
            if(null == param){
                continue;
            }
            try {
                field.setAccessible(true);
                String key = prefix + "." + param.value();
                ans.putIfAbsent(key, new ArrayList<>());
                ans.get(key).add(field.get(obj));
            } catch (IllegalAccessException e) {
            }
        }
    }

    public static String parseAndConvert(String tmpl, Map<String, List<Object>> params, int idx, FunctionManger funcManger) {
        /*${name} -> name*/
        Map<String, String> fields = parseFields(tmpl);

        /*参数替换*/
        for (Map.Entry<String, String> entry : fields.entrySet()) {
            List<Object> list = params.get(entry.getValue());
            String val = "undefined";
            if(null != list && idx < list.size()){
                val = String.valueOf(list.get(idx));
            }
            tmpl = tmpl.replace(entry.getKey(), val);
        }
        /*函数处理*/
        return convertByFunc(tmpl,funcManger);
    }


    public static List<TemplateElement> parseTemplate(String tmpl) {
        List<TemplateElement> ans = new ArrayList<>();
        Pattern pattern = Pattern.compile(TemplateConstant.REGEX_TABLE);
        Matcher matcher = pattern.matcher(tmpl);
        int tmplIdx = 0;
        while(matcher.find()){
            int start = matcher.start();
            int end = matcher.end();
            String tablePrefix = matcher.group(1);
            String tableBody = matcher.group(2);
            String tableSuffix = matcher.group(3);
            if(tmplIdx < start){
                ans.add(new ContentElement(tmpl.substring(tmplIdx,start)));
            }
            List<RowElement> rows = parseRows(tableBody);
            ans.add(new TableElement(tablePrefix,rows,tableSuffix));
            tmplIdx = end;
        }
        if(tmplIdx < tmpl.length()){
            ans.add(new ContentElement(tmpl.substring(tmplIdx)));
        }
        return ans;
    }

    private static List<RowElement> parseRows(String tmpl) {
        List<RowElement> ans = new ArrayList<>();
        Pattern pattern = Pattern.compile(TemplateConstant.REGEX_ROW);
        Matcher matcher = pattern.matcher(tmpl);
        while(matcher.find()){
            String row = matcher.group();
            String rowPrefix = matcher.group(1);
            String rowBody = matcher.group(2);
            String rowSuffix = matcher.group(3);

            List<ColumnElement> columns = parseColumn(rowBody);
            ans.add(new RowElement(rowPrefix,columns,row,rowSuffix));
        }
        return ans;
    }

    private static List<ColumnElement> parseColumn(String rowBody) {
        List<ColumnElement> ans = new ArrayList<>();
        Pattern pattern = Pattern.compile(TemplateConstant.REGEX_COLUMN);
        Matcher matcher = pattern.matcher(rowBody);
        while(matcher.find()){
            String columnPrefix = matcher.group(1);
            String columnBody = matcher.group(3);
            String columnSuffix = matcher.group(4);
            ans.add(new ColumnElement(columnPrefix,new ContentElement(columnBody),columnSuffix));
        }
        return ans;
    }
}
