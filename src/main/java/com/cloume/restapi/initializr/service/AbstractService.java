package com.cloume.restapi.initializr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public abstract class AbstractService {

    @Autowired
    protected MongoTemplate mongoTemplate;
}
