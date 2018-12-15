/**
 * 
 */
package com.cirb.archive.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import com.cirb.archive.domain.Archive;
import com.cirb.archive.domain.JsonArchive;
import com.cirb.archive.domain.vo.ArchiveVO;
import com.cirb.archive.domain.vo.SearchVO;

/**
 * @author Zkaoukab
 *
 */
public interface IArchiveService {

	/**
	 * @param vo
	 * @return
	 */
	List<JsonArchive> search(SearchVO vo) throws NoSuchAlgorithmException;

	/**
	 * @return
	 */
	List<Archive> findAll();

	/**
	 * @param id
	 * @return
	 */
	Optional<Archive> findById(String id) throws NoSuchAlgorithmException, IOException;

}
