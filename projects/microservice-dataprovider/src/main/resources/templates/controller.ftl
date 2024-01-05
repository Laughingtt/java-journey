package com.laughing.microservice.dataprovider.controller;

import com.alibaba.fastjson.JSONObject;
import com.laughing.microservice.dataprovider.dao.${class_name}Dao;
import com.laughing.microservice.dataprovider.entity.${class_name};
import com.laughing.microservice.dataprovider.in.${class_name}In;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
* @date 2023/10/23
*/
@Slf4j
@RestController
@RequestMapping("/demo/")
public class ${class_name}Controller extends BaseController {

@Resource
private ${class_name}Dao ${class_name}Dao;

@ApiOperation(value = "", notes = "", httpMethod = "POST")
@PostMapping({"/get_custom_v1"})
public JSONObject get_custom_v1(@RequestBody @Validated ${class_name}In ${class_name}In, HttpServletRequest request) throws Exception {

log.info("请求业务流参数：url[{}], body[{}]", "${class_name}In", ${class_name}In.toString());
return ReqExecutor(${class_name}In, request);
}

@Override
public JSONObject getQueryResult(Object object) {

${class_name}In ${class_name}In = (${class_name}In) object;

Integer customerID = Integer.valueOf(${class_name}In.getCustomerId());

List<${class_name}> ${class_name} = ${class_name}Dao.selectById(customerID);

log.info("query result is {}", ${class_name});
JSONObject jsonObject = new JSONObject();
jsonObject.put("result", Arrays.toString(${class_name}.toArray()));

return jsonObject;
}

}
