/**
 * 
 */
package com.cirb.archive.repositories;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.cirb.archive.domain.Archive;

/**
 * @author Zkaoukab
 *
 */
public interface ArchiveRepository extends SolrCrudRepository<Archive, Long> {

}
