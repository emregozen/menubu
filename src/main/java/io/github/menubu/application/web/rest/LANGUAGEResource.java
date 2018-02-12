package io.github.menubu.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.menubu.application.domain.LANGUAGE;

import io.github.menubu.application.repository.LANGUAGERepository;
import io.github.menubu.application.repository.search.LANGUAGESearchRepository;
import io.github.menubu.application.web.rest.errors.BadRequestAlertException;
import io.github.menubu.application.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing LANGUAGE.
 */
@RestController
@RequestMapping("/api")
public class LANGUAGEResource {

    private final Logger log = LoggerFactory.getLogger(LANGUAGEResource.class);

    private static final String ENTITY_NAME = "lANGUAGE";

    private final LANGUAGERepository lANGUAGERepository;

    private final LANGUAGESearchRepository lANGUAGESearchRepository;

    public LANGUAGEResource(LANGUAGERepository lANGUAGERepository, LANGUAGESearchRepository lANGUAGESearchRepository) {
        this.lANGUAGERepository = lANGUAGERepository;
        this.lANGUAGESearchRepository = lANGUAGESearchRepository;
    }

    /**
     * POST  /languages : Create a new lANGUAGE.
     *
     * @param lANGUAGE the lANGUAGE to create
     * @return the ResponseEntity with status 201 (Created) and with body the new lANGUAGE, or with status 400 (Bad Request) if the lANGUAGE has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/languages")
    @Timed
    public ResponseEntity<LANGUAGE> createLANGUAGE(@RequestBody LANGUAGE lANGUAGE) throws URISyntaxException {
        log.debug("REST request to save LANGUAGE : {}", lANGUAGE);
        if (lANGUAGE.getId() != null) {
            throw new BadRequestAlertException("A new lANGUAGE cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LANGUAGE result = lANGUAGERepository.save(lANGUAGE);
        lANGUAGESearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/languages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /languages : Updates an existing lANGUAGE.
     *
     * @param lANGUAGE the lANGUAGE to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated lANGUAGE,
     * or with status 400 (Bad Request) if the lANGUAGE is not valid,
     * or with status 500 (Internal Server Error) if the lANGUAGE couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/languages")
    @Timed
    public ResponseEntity<LANGUAGE> updateLANGUAGE(@RequestBody LANGUAGE lANGUAGE) throws URISyntaxException {
        log.debug("REST request to update LANGUAGE : {}", lANGUAGE);
        if (lANGUAGE.getId() == null) {
            return createLANGUAGE(lANGUAGE);
        }
        LANGUAGE result = lANGUAGERepository.save(lANGUAGE);
        lANGUAGESearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, lANGUAGE.getId().toString()))
            .body(result);
    }

    /**
     * GET  /languages : get all the lANGUAGES.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of lANGUAGES in body
     */
    @GetMapping("/languages")
    @Timed
    public List<LANGUAGE> getAllLANGUAGES() {
        log.debug("REST request to get all LANGUAGES");
        return lANGUAGERepository.findAll();
        }

    /**
     * GET  /languages/:id : get the "id" lANGUAGE.
     *
     * @param id the id of the lANGUAGE to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the lANGUAGE, or with status 404 (Not Found)
     */
    @GetMapping("/languages/{id}")
    @Timed
    public ResponseEntity<LANGUAGE> getLANGUAGE(@PathVariable Long id) {
        log.debug("REST request to get LANGUAGE : {}", id);
        LANGUAGE lANGUAGE = lANGUAGERepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(lANGUAGE));
    }

    /**
     * DELETE  /languages/:id : delete the "id" lANGUAGE.
     *
     * @param id the id of the lANGUAGE to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/languages/{id}")
    @Timed
    public ResponseEntity<Void> deleteLANGUAGE(@PathVariable Long id) {
        log.debug("REST request to delete LANGUAGE : {}", id);
        lANGUAGERepository.delete(id);
        lANGUAGESearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/languages?query=:query : search for the lANGUAGE corresponding
     * to the query.
     *
     * @param query the query of the lANGUAGE search
     * @return the result of the search
     */
    @GetMapping("/_search/languages")
    @Timed
    public List<LANGUAGE> searchLANGUAGES(@RequestParam String query) {
        log.debug("REST request to search LANGUAGES for query {}", query);
        return StreamSupport
            .stream(lANGUAGESearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
