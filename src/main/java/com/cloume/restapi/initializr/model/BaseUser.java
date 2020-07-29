package com.cloume.restapi.initializr.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 基础用户实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class BaseUser extends BaseResource {

    /**
     * 姓名
     */
    private String name;

    /**
     * 用户类型
     */
    private String type;

    /**
     * 性别
     */
    private String gender;
}
