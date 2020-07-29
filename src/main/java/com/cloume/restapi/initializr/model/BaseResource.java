package com.cloume.restapi.initializr.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.data.mongodb.core.query.Criteria;

/**
 * @author yxiao
 * @date 2020-07-29
 * @description 基础资源类
 */
@Data
@NoArgsConstructor
public abstract class BaseResource {

    /**
     * 创建时间
     */
    private Long createdTime = System.currentTimeMillis();

    /**
     * 是否被移除
     */
    private Boolean isRemoved = false;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 获取没有被移除的 Criteria
     * @return
     */
    @JsonIgnore
    public static Criteria neRemoved() {
        return Criteria.where("isRemoved").ne(true);
    }
}
