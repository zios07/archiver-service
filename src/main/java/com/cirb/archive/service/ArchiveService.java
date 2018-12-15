/**
 *
 */
package com.cirb.archive.service;

import com.cirb.archive.config.security.JavaPGP;
import com.cirb.archive.domain.Archive;
import com.cirb.archive.domain.JsonArchive;
import com.cirb.archive.domain.vo.SearchVO;
import com.cirb.archive.repositories.ArchiveRepository;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.convert.DateTimeConverters.JavaDateConverter;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

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
  public List<JsonArchive> search(SearchVO vo) throws NoSuchAlgorithmException {
    Criteria criteria = new Criteria("date");
    if (vo.getDateFrom() != null && vo.getDateTo() != null) {
      String start = JavaDateConverter.INSTANCE.convert(vo.getDateFrom()) + "/DAY";
      String end = JavaDateConverter.INSTANCE.convert(vo.getDateTo()) + "/DAY";
      criteria.expression("[" + start + " TO " + end + "]");
    } else if (vo.getTimestamp() != null) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(vo.getTimestamp());
      calendar.add(Calendar.DAY_OF_MONTH, 1);
      String date = JavaDateConverter.INSTANCE.convert(vo.getTimestamp()) + "/DAY";
      String nextDate = JavaDateConverter.INSTANCE.convert(calendar.getTime()) + "/DAY";

      criteria.expression("[" + date + " TO " + nextDate + "]");
    }
    Query query = new SimpleQuery(criteria);
    Page<Archive> result = solrTemplate.query("archives", query, Archive.class);
    return applyFilters(result.getContent(), vo);
  }

  private List<JsonArchive> applyFilters(List<Archive> archives, SearchVO vo) throws NoSuchAlgorithmException {
    List<JsonArchive> result = new ArrayList<>();
    JavaPGP javaPGP = JavaPGP.getInstance();
    boolean withFilter = !Strings.isNullOrEmpty(vo.getTransactionId()) || !Strings.isNullOrEmpty(vo.getKeyValue());
    archives.stream().forEach(archive -> {
      Gson gson = new Gson();
      Type archiveListType = new TypeToken<List<JsonArchive>>() {
      }.getType();
      String json = new String(ArrayUtils.toPrimitive(archive.getContent())) + "]";
      List<JsonArchive> encryptedArchives = gson.fromJson(json, archiveListType);
      encryptedArchives.stream().forEach(jsonArchive -> {
        try {
          jsonArchive.getConsumer().setInstitute(javaPGP.decrypt(jsonArchive.getConsumer().getInstitute(), javaPGP.getAESKey()));
          jsonArchive.getConsumer().setLegalContext(javaPGP.decrypt(jsonArchive.getConsumer().getLegalContext(), javaPGP.getAESKey()));
          jsonArchive.getProvider().setInstitute(javaPGP.decrypt(jsonArchive.getProvider().getInstitute(), javaPGP.getAESKey()));
          jsonArchive.getProvider().setLegalContext(javaPGP.decrypt(jsonArchive.getProvider().getLegalContext(), javaPGP.getAESKey()));
          if (withFilter) {
            if (!Strings.isNullOrEmpty(vo.getTransactionId()) && !Strings.isNullOrEmpty(vo.getKeyValue())) {
              if (jsonArchive.getConsumer().getTransactionId().equals(vo.getTransactionId()) && jsonArchive.getConsumer().getKeyValue().equals(vo.getKeyValue())) {
                result.add(jsonArchive);
              }
            } else if (!Strings.isNullOrEmpty(vo.getKeyValue()) && Strings.isNullOrEmpty(vo.getTransactionId())) {
              if (jsonArchive.getConsumer().getKeyValue().equals(vo.getKeyValue())) {
                result.add(jsonArchive);
              }
            } else if (Strings.isNullOrEmpty(vo.getKeyValue()) && !Strings.isNullOrEmpty(vo.getTransactionId())) {
              if (jsonArchive.getConsumer().getTransactionId().equals(vo.getTransactionId())) {
                result.add(jsonArchive);
              }
            }
          } else {
            result.add(jsonArchive);
          }
        } catch (GeneralSecurityException e) {
          e.printStackTrace();
        }
      });
    });
    return result;
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
  public Optional<Archive> findById(String id) throws NoSuchAlgorithmException, IOException {
    JavaPGP javaPGP = JavaPGP.getInstance();
    Archive archive = repo.findById(id).get();
    if (archive != null) {
      Byte[] content = archive.getContent();
      Gson gson = new Gson();
      Type archiveListType = new TypeToken<List<JsonArchive>>() {
      }.getType();
      String json = new String(ArrayUtils.toPrimitive(archive.getContent())) + "]";
      List<JsonArchive> encryptedArchives = gson.fromJson(json, archiveListType);
//      encryptedArchives.stream().forEach(jsonArchive -> {
//        try {
//          jsonArchive.getConsumer().setInstitute(javaPGP.decrypt(jsonArchive.getConsumer().getInstitute(), javaPGP.getAESKey()));
//          jsonArchive.getConsumer().setLegalContext(javaPGP.decrypt(jsonArchive.getConsumer().getLegalContext(), javaPGP.getAESKey()));
//          jsonArchive.getProvider().setInstitute(javaPGP.decrypt(jsonArchive.getProvider().getInstitute(), javaPGP.getAESKey()));
//          jsonArchive.getProvider().setLegalContext(javaPGP.decrypt(jsonArchive.getProvider().getLegalContext(), javaPGP.getAESKey()));
//        } catch (GeneralSecurityException e) {
//          e.printStackTrace();
//        }
//      });
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      ObjectOutputStream oos = new ObjectOutputStream(bos);
      oos.writeObject(encryptedArchives);
      byte[] bytes = bos.toByteArray();
      archive.setContent(ArrayUtils.toObject(bytes));
    }
    return Optional.of(archive);
  }

}
