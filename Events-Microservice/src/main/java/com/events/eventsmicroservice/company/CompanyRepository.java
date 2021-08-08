package com.events.eventsmicroservice.company;

import com.mongodb.client.MongoIterable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends MongoRepository<Company, String> {
    List findAllByCompanyId(String id);
}
