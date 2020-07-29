package com.cloume.restapi.initializr.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 用于处理分页
 * @param <T>
 */
@NoArgsConstructor
public class Paging<T> implements Serializable {

    public static <T> Paging of(T data, long count) {
        return new Paging(data, count);
    }

    private Paging(T data, long count) {
        this.data = data;
        this.count = count;
    }

    @Setter
    @Getter
    private Long count;

    @Setter
    @Getter
    private T data;
}
