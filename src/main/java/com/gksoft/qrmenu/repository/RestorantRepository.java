package com.gksoft.qrmenu.repository;

import com.gksoft.qrmenu.domain.Restorant;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Restorant entity.
 */
@Repository
public interface RestorantRepository extends MongoRepository<Restorant, String> {
    @Query("{}")
    Page<Restorant> findAllWithEagerRelationships(Pageable pageable);

    @Query("{}")
    List<Restorant> findAllWithEagerRelationships();

    @Query("{'id': ?0}")
    Optional<Restorant> findOneWithEagerRelationships(String id);
}
