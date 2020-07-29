package com.cloume.restapi.initializr.service.impl;

import com.cloume.restapi.initializr.model.BaseResource;
import com.cloume.restapi.initializr.model.BaseUser;
import com.cloume.restapi.initializr.model.Paging;
import com.cloume.restapi.initializr.repository.BaseUserRepository;
import com.cloume.restapi.initializr.service.AbstractService;
import com.cloume.restapi.initializr.service.IBaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yxiao
 * @date
 * @description
 */
@Service
public class BaseUserServiceImpl extends AbstractService implements IBaseUserService {

    @Autowired
    private BaseUserRepository baseUserRespository;

    @Override
    public BaseUser create() {
        return new BaseUser();
    }

    @Override
    public MongoRepository<BaseUser, String> getRepository() {
        return baseUserRespository;
    }

    @Override
    public Paging<List<BaseUser>> list(int page, int size) {
        Criteria criteria = BaseResource.neRemoved();

        return Paging.of(mongoTemplate.find(Query.query(criteria).with(PageRequest.of(page, size)), BaseUser.class),
                mongoTemplate.count(Query.query(criteria), BaseUser.class));
    }
}
