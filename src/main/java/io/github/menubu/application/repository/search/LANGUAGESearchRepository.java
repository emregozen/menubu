package io.github.menubu.application.repository.search;

import io.github.menubu.application.domain.LANGUAGE;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the LANGUAGE entity.
 */
public interface LANGUAGESearchRepository extends ElasticsearchRepository<LANGUAGE, Long> {
}
