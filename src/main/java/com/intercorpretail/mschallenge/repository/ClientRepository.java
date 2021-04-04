package com.intercorpretail.mschallenge.repository;

import model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientRepository extends MongoRepository<Client, String> {
}