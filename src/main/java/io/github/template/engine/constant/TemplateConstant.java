package io.github.template.engine.constant;

/**
 * @author renmw
 * @create 2024/5/12 17:50
 **/
public class TemplateConstant {
    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String REGEX_FIELD = "\\$\\{([0-9a-zA-Z_.]+)\\}";
    public static final String REGEX_FUNC = "\\$([0-9a-zA-Z-_.]+)\\((([^()])*)\\)";

    public static final String REGEX_TABLE = "(<table[^>]*>)(?s)(.*?)<\\/table>";
    public static final String REGEX_TR = "(<tr[^>]*>)(?s)(.*?)<\\/tr>";
    public static final String REGEX_TH = "(<th[^>]*>)(?s)(.*?)<\\/th>";
    public static final String REGEX_TD = "(<td[^>]*>)(?s)(.*?)<\\/td>";

    public static final String TABLE_SUFFIX = "</table>";
    public static final String TR_SUFFIX = "</tr>";
    public static final String TH_SUFFIX = "</th>";
    public static final String TD_SUFFIX = "</td>";
}
