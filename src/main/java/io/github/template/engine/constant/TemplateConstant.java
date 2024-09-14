package io.github.template.engine.constant;

/**
 * @author renmw
 * @create 2024/5/12 17:50
 **/
public class TemplateConstant {
    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String REGEX_FIELD = "\\$\\{([0-9a-zA-Z_.]+)\\}";
    public static final String REGEX_FUNC = "\\$([0-9a-zA-Z-_.]+)\\((([^()])*)\\)";

    /*(?s) 启动单行模式， .可以匹配换行符\n*/
    public static final String REGEX_TABLE = "(<table[^>]*>)(?s)(.*?)(<\\/table>)";
    public static final String REGEX_ROW = "(<tr[^>]*>)(?s)(.*?)(<\\/tr>)";
    /* \2 反向引用，用于匹配一个先前捕获的组，2代表第二个group 即(th|td) */
    public static final String REGEX_COLUMN = "(<(th|td)[^>]*>)(?s)(.*?)(<\\/\\2>)";
}
