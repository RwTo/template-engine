package io.github.template.engine.domain;

import io.github.template.engine.func.FunctionManger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author renmw
 * @create 2024/9/14 17:03
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConvertSource {
    private Map<String, List<Object>> params;
    private FunctionManger functionManger;
    private int rowIdx;
}
