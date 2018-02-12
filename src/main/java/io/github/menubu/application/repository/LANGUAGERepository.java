package io.github.menubu.application.repository;

import io.github.menubu.application.domain.LANGUAGE;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the LANGUAGE entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LANGUAGERepository extends JpaRepository<LANGUAGE, Long> {

}
