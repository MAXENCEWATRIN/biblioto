package com.maxencew.biblioto.infrastructure.configuration;

import com.mongodb.client.MongoClients;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoDbConfiguration {
    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(MongoClients.create("mongodb://localhost:27017"), "biblioto");
    }

    @Bean
    public GridFSBucket gridFSBucket() {
        return GridFSBuckets.create(mongoTemplate().getDb());
    }
}
