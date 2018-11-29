/**
 *
 */
package com.cirb.archive.rest;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import com.cirb.archive.domain.vo.ArchiveVO;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cirb.archive.domain.Archive;
import com.cirb.archive.domain.vo.SearchVO;
import com.cirb.archive.service.IArchiveService;
import com.google.common.collect.Lists;

/**
 * @author Zkaoukab
 */

@RestController
@RequestMapping(value = "api/archives")
public class SearchController {

  @Autowired
  private IArchiveService archiveService;

  @GetMapping
  public ResponseEntity<List<Archive>> getAll() {
    return ResponseEntity.ok(archiveService.findAll());
  }

  @PostMapping(value = "search")
  public ResponseEntity<List<ArchiveVO>> getByDate(@RequestBody SearchVO vo) {
    return ResponseEntity.ok(Lists.newArrayList(archiveService.search(vo)));
  }

  @GetMapping(value = "download/{id}")
  public ResponseEntity<byte[]> download(@PathVariable String id, HttpServletResponse response) throws IOException {
    Optional<Archive> optional = archiveService.findById(id);
    if (optional.isPresent()) {
      Archive archive = optional.get();
      final HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.valueOf("application/octet-stream"));

      String disposition = "inline";

      headers.add("content-disposition",
        disposition + "; filename=\"" + archive.getFileName() + "." + archive.getExtension() + "\"");
      return new ResponseEntity<>(ArrayUtils.toPrimitive(archive.getContent()), headers, HttpStatus.OK);
    }
    return ResponseEntity.notFound().build();
  }

}
