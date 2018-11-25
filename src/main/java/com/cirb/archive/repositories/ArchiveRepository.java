/**
 * 
 */
package com.cirb.archive.repositories;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.cirb.archive.domain.Archive;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Zkaoukab
 *
 */
@Repository
public interface ArchiveRepository extends SolrCrudRepository<Archive, String> {

  List<Archive> findByDateBetween(Date from, Date to);

}
