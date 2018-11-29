/**
 * 
 */
package com.cirb.archive.service;

import java.util.List;
import java.util.Optional;

import com.cirb.archive.domain.Archive;
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
	List<ArchiveVO> search(SearchVO vo);

	/**
	 * @return
	 */
	List<Archive> findAll();

	/**
	 * @param id
	 * @return
	 */
	Optional<Archive> findById(String id);

}
