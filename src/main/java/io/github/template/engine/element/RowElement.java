package io.github.template.engine.element;


import io.github.template.engine.constant.TemplateConstant;
import io.github.template.engine.domain.ConvertSource;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author renmw
 * @create 2024/9/13 12:06
 **/
public class RowElement implements TemplateElement {
    private final String prefix;
    private final List<ColumnElement> columns;
    private final String suffix;
    private final String content;

    public RowElement(String prefix, List<ColumnElement> columns, String content, String suffix) {
        this.prefix = prefix;
        this.columns = columns;
        this.content = content;
        this.suffix = suffix;
    }


    @Override
    public String getContent(ConvertSource convertSource) {
        StringBuilder ans = new StringBuilder();
        Pattern pattern = Pattern.compile(TemplateConstant.REGEX_FIELD);
        Matcher matcher = pattern.matcher(content);
        if(matcher.find()){
            List<Object> fields = convertSource.getParams().get(matcher.group(1));
            int total = null == fields ? 1 : fields.size();
            int rowIdx = 0;
            while(rowIdx < total){
                ans.append(this.prefix);
                for (ColumnElement column : this.columns) {
                    convertSource.setRowIdx(rowIdx);
                    ans.append(column.getContent(convertSource));
                }
                ans.append(this.suffix);
                rowIdx++;
            }
            convertSource.setRowIdx(0);
            return ans.toString();
        }
        ans.append(this.prefix);
        for (ColumnElement column : this.columns) {
            ans.append(column.getContent(convertSource));
        }
        ans.append(this.suffix);
        return ans.toString();
    }
}
