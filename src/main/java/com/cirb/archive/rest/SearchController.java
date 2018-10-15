/**
 * 
 */
package com.cirb.archive.rest;

import java.util.List;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cirb.archive.domain.Archive;
import com.cirb.archive.repositories.ArchiveRepository;

/**
 * @author Zkaoukab
 *
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
	
}
