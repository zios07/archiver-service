/**
 * 
 */
package com.cirb.archive.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import com.cirb.archive.domain.Archive;
import com.cirb.archive.domain.Result;
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
	Result search(SearchVO vo) throws NoSuchAlgorithmException;

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
