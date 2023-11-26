package com.laughing.microservice.dataprovider.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @description: 客户信息
 * @author: XXM
 * @date: 2023/7/11 17:25
 */
@Data
@TableName(value = "cust_base_info")
public class CustBaseInfo {
    /**
     * '流水id'
     */
    @TableId(type = IdType.AUTO)
    Long custRowId;
    /**
     * '客户名称'
     */
    String custName;
    /**
     * '客户标识'
     */
    String custFlag;
    /**
     *  客户描述
     */
    String custDesc;
    /**
     * 第一联系人姓名
     */
    String firstLinkmanName;
    /**
     * 联系人邮件
     */
    String firstLinkmanEmail;
    /**
     * '第一联系人手机号'
     */
    String firstLinkmanMobile;
    /**
     * 主要联系人所属部门
     */
    String firstLinkmanDept;
    /**
     * 客户状态
     */
    private Integer custStatus;
    /**
     * '当前商务维护人id'
     */
    Long bizManagerRowId;
    /**
     * ''当前客户经理名称''
     */
    String bizManagerName;
    /**
     * ''当前客户经理接手时间''
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date bizManagerTakeoverTime;
    /**
     * ''客户经理变更原因''
     */
    String bizManagerChgReason;

    /**
     * '当前解决方案维护人id'
     */
    Long plannerRowId;
    /**
     *  当前客户解决方案名称
     */
    String plannerName;
    /**
     *  当前客户解决方案设计人接手时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    Date plannerTakeoverTime;

    /**
     * 方案规划人变更原因''
     */
    String plannerChgReason;

    Long createUserRowId;
    /**
     * '创建人姓名'
     */
    String createUserName;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date createTime;

    Long lastEditUserRowId;
    /**
     * 最后修改人
     */
    String lastEditUserName;
    /**
     * 最后修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date lastEditTime;

    private Integer deleted;
    /**
     * 客户类型： 0：测试客户 1：正式客户
     */
    Integer custType;
    /**
     * 客户简称
     */
    String nickName;
    /**
     * 所属地区
     */
    String district;
    /**
     * 所属行业
     */
    String industry;
    /**
     * 是否关联企业
     */
    private Integer relatedEnterprise;
    /**
     * 销售模式
     */
    String saleModel;
}
