package com.anakin.generator.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 接口返回Json类型数据实体
 * @author: yujie
 * @date 2019/4/24 10:40
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(description = "返回响应体,不带data数据")
public class ApiResult {

    @ApiModelProperty(value = "响应代码")
    protected String code;
    @ApiModelProperty(value = "返回信息")
    protected String message;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"code\":")
                .append(code);
        sb.append(",\"message\":\"")
                .append(message).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
