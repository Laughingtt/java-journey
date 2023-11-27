package com.laughing.microservice.dataprovider.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@ApiModel(value = "", description = "")
public class OrdersIn implements Serializable {

    /**
     * CustomerID
     */
    @NotEmpty(message = "CustomerID 不能为空")
    @JsonProperty("CustomerId")
    @ApiModelProperty(value = "顾客id", example = "310101199001010012")
    private String CustomerId;

}
