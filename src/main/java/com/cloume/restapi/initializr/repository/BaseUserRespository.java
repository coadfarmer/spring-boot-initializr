package com.cloume.restapi.initializr.repository;

import com.cloume.restapi.initializr.model.BaseUser;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author yxiao
 * @date
 * @description
 */
public interface BaseUserRespository extends MongoRepository<BaseUser, String> {
}
