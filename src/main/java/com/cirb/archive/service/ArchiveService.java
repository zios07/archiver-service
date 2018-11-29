/**
 *
 */
package com.cirb.archive.service;

import java.util.List;
import java.util.Optional;

import com.cirb.archive.domain.vo.ArchiveVO;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.convert.DateTimeConverters.JavaDateConverter;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.SimpleStringCriteria;
import org.springframework.stereotype.Service;

import com.cirb.archive.domain.Archive;
import com.cirb.archive.domain.vo.SearchVO;
import com.cirb.archive.repositories.ArchiveRepository;

/**
 * @author Zkaoukab
 */

@Service
public class ArchiveService implements IArchiveService {

  @Autowired
  private ArchiveRepository repo;

  @Autowired
  private SolrTemplate solrTemplate;

  /* (non-Javadoc)
   * @see com.cirb.archive.service.IArchiveService#search(com.cirb.archive.domain.vo.SearchVO)
   */
  @Override
  public List<ArchiveVO> search(SearchVO vo) {
    Criteria conditions = null;
    String start = JavaDateConverter.INSTANCE.convert(vo.getDateFrom()) + "/DAY";
    String end = JavaDateConverter.INSTANCE.convert(vo.getDateTo()) + "/DAY";
    Criteria criteria = new Criteria("date").expression("[" + start + " TO " + end + "]");
//    new SimpleStringCriteria("[" + start + " TO " + end + "]");
//    if (vo.getDateFrom() != null && vo.getDateTo() != null)
//      conditions = new Criteria("date").between(start, end, true, true);
//    else if (vo.getTimestamp() != null)
//      conditions = new Criteria("date").is(vo.getTimestamp());

    SimpleQuery search = new SimpleQuery(criteria);
    Page<ArchiveVO> results = solrTemplate.query("archives", search, ArchiveVO.class);
    return results.getContent();
  }

  /* (non-Javadoc)
   * @see com.cirb.archive.service.IArchiveService#findAll()
   */
  @Override
  public List<Archive> findAll() {
    return Lists.newArrayList(repo.findAll());
  }

  /* (non-Javadoc)
   * @see com.cirb.archive.service.IArchiveService#findById(java.lang.String)
   */
  @Override
  public Optional<Archive> findById(String id) {
    return repo.findById(id);
  }

}
