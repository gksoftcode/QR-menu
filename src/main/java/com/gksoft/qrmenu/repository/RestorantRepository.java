package com.gksoft.qrmenu.repository;

import com.gksoft.qrmenu.domain.Restorant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Restorant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RestorantRepository extends MongoRepository<Restorant, String> {}
