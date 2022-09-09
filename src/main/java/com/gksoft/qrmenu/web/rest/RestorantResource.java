package com.gksoft.qrmenu.web.rest;

import com.gksoft.qrmenu.domain.Restorant;
import com.gksoft.qrmenu.repository.RestorantRepository;
import com.gksoft.qrmenu.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.gksoft.qrmenu.domain.Restorant}.
 */
@RestController
@RequestMapping("/api")
public class RestorantResource {

    private final Logger log = LoggerFactory.getLogger(RestorantResource.class);

    private static final String ENTITY_NAME = "restorant";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RestorantRepository restorantRepository;

    public RestorantResource(RestorantRepository restorantRepository) {
        this.restorantRepository = restorantRepository;
    }

    /**
     * {@code POST  /restorants} : Create a new restorant.
     *
     * @param restorant the restorant to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new restorant, or with status {@code 400 (Bad Request)} if the restorant has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/restorants")
    public ResponseEntity<Restorant> createRestorant(@RequestBody Restorant restorant) throws URISyntaxException {
        log.debug("REST request to save Restorant : {}", restorant);
        if (restorant.getId() != null) {
            throw new BadRequestAlertException("A new restorant cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Restorant result = restorantRepository.save(restorant);
        return ResponseEntity
            .created(new URI("/api/restorants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /restorants/:id} : Updates an existing restorant.
     *
     * @param id the id of the restorant to save.
     * @param restorant the restorant to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated restorant,
     * or with status {@code 400 (Bad Request)} if the restorant is not valid,
     * or with status {@code 500 (Internal Server Error)} if the restorant couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/restorants/{id}")
    public ResponseEntity<Restorant> updateRestorant(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Restorant restorant
    ) throws URISyntaxException {
        log.debug("REST request to update Restorant : {}, {}", id, restorant);
        if (restorant.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, restorant.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!restorantRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Restorant result = restorantRepository.save(restorant);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, restorant.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /restorants/:id} : Partial updates given fields of an existing restorant, field will ignore if it is null
     *
     * @param id the id of the restorant to save.
     * @param restorant the restorant to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated restorant,
     * or with status {@code 400 (Bad Request)} if the restorant is not valid,
     * or with status {@code 404 (Not Found)} if the restorant is not found,
     * or with status {@code 500 (Internal Server Error)} if the restorant couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/restorants/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Restorant> partialUpdateRestorant(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Restorant restorant
    ) throws URISyntaxException {
        log.debug("REST request to partial update Restorant partially : {}, {}", id, restorant);
        if (restorant.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, restorant.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!restorantRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Restorant> result = restorantRepository
            .findById(restorant.getId())
            .map(existingRestorant -> {
                if (restorant.getLat() != null) {
                    existingRestorant.setLat(restorant.getLat());
                }
                if (restorant.getLang() != null) {
                    existingRestorant.setLang(restorant.getLang());
                }
                if (restorant.getIsActive() != null) {
                    existingRestorant.setIsActive(restorant.getIsActive());
                }
                if (restorant.getIsDeleted() != null) {
                    existingRestorant.setIsDeleted(restorant.getIsDeleted());
                }
                if (restorant.getLogoUrl() != null) {
                    existingRestorant.setLogoUrl(restorant.getLogoUrl());
                }
                if (restorant.getFbUrl() != null) {
                    existingRestorant.setFbUrl(restorant.getFbUrl());
                }
                if (restorant.getInstaUrl() != null) {
                    existingRestorant.setInstaUrl(restorant.getInstaUrl());
                }
                if (restorant.getTwitterUrl() != null) {
                    existingRestorant.setTwitterUrl(restorant.getTwitterUrl());
                }
                if (restorant.getYoutubeUrl() != null) {
                    existingRestorant.setYoutubeUrl(restorant.getYoutubeUrl());
                }
                if (restorant.getGoogleUrl() != null) {
                    existingRestorant.setGoogleUrl(restorant.getGoogleUrl());
                }

                return existingRestorant;
            })
            .map(restorantRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, restorant.getId())
        );
    }

    /**
     * {@code GET  /restorants} : get all the restorants.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of restorants in body.
     */
    @GetMapping("/restorants")
    public ResponseEntity<List<Restorant>> getAllRestorants(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        @RequestParam(required = false, defaultValue = "false") boolean eagerload
    ) {
        log.debug("REST request to get a page of Restorants");
        Page<Restorant> page;
        if (eagerload) {
            page = restorantRepository.findAllWithEagerRelationships(pageable);
        } else {
            page = restorantRepository.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /restorants/:id} : get the "id" restorant.
     *
     * @param id the id of the restorant to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the restorant, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/restorants/{id}")
    public ResponseEntity<Restorant> getRestorant(@PathVariable String id) {
        log.debug("REST request to get Restorant : {}", id);
        Optional<Restorant> restorant = restorantRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(restorant);
    }

    /**
     * {@code DELETE  /restorants/:id} : delete the "id" restorant.
     *
     * @param id the id of the restorant to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/restorants/{id}")
    public ResponseEntity<Void> deleteRestorant(@PathVariable String id) {
        log.debug("REST request to delete Restorant : {}", id);
        restorantRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
