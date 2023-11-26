package com.laughing.microservice.dataprovider.in;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author linld
 */
@Data
@ApiModel(value = "", description = "")
public class SingleMaritalVerifyIn implements Serializable {

    /**
     * 姓名
     */
    @NotEmpty(message = "name 不能为空")
    @ApiModelProperty(value = "姓名", example = "王一")
    private String name;


}
