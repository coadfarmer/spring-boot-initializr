package com.cloume.restapi.initializr.service;

import com.cloume.restapi.initializr.model.BaseUser;
import com.cloume.restapi.initializr.model.Paging;

import java.util.List;

/**
 * @author yxiao
 * @date
 * @description
 */
public interface IBaseUserService extends IBaseService<BaseUser> {
    Paging<List<BaseUser>> list(int page, int size);
}
