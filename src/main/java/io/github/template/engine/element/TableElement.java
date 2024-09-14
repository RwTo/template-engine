package io.github.template.engine.element;



import io.github.template.engine.constant.TemplateConstant;
import io.github.template.engine.domain.ConvertSource;

import java.util.List;

/**
 * @author renmw
 * @create 2024/9/13 12:05
 **/
public class TableElement implements TemplateElement {
    private final String prefix;
    private final List<RowElement> rows;
    private final String suffix;

    public TableElement(String prefix, List<RowElement> rows, String suffix) {
        this.prefix = prefix;
        this.rows = rows;
        this.suffix = suffix;
    }

    @Override
    public String getContent(ConvertSource convertSource) {
        StringBuilder ans = new StringBuilder();
        ans.append(this.prefix);
        for (RowElement row : this.rows) {
            ans.append(row.getContent(convertSource));
        }
        ans.append(this.suffix);
        return ans.toString();
    }
}
