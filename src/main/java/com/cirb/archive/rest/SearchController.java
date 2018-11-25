/**
 *
 */
package com.cirb.archive.rest;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import com.google.common.collect.Lists;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cirb.archive.domain.Archive;
import com.cirb.archive.repositories.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Zkaoukab
 */

@RestController
@RequestMapping(value = "archives")
public class SearchController {

  @Autowired
  private ArchiveRepository archiveRepository;

  @GetMapping
  public ResponseEntity<List<Archive>> getAll() {
    return ResponseEntity.ok(Lists.newArrayList(archiveRepository.findAll()));
  }

  @GetMapping(value = "search")
  public ResponseEntity<List<Archive>> getByDate(@RequestParam Date from, @RequestParam Date to) {
    return ResponseEntity.ok(Lists.newArrayList(archiveRepository.findByDateBetween(from, to)));
  }

  @GetMapping(value = "download/{id}")
  public ResponseEntity download(@PathVariable String id, HttpServletResponse response) throws IOException {
    Optional<Archive> optional = archiveRepository.findById(id);
    if (optional.isPresent()) {
      Archive archive = optional.get();
      try (InputStream is = new ByteArrayInputStream(ArrayUtils.toPrimitive(archive.getContent()))) {
        IOUtils.copy(is, response.getOutputStream());
      }
      response.setHeader("Content-Disposition", "attachment; filename=" + archive.getFileName() + "." + archive.getExtension());
      response.flushBuffer();
    }
    return ResponseEntity.notFound().build();
  }

}
