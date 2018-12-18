/**
 * 
 */
package com.cirb.archive.domain;

import java.util.List;

/**
 * @author Zkaoukab
 *
 */
public class Result {

	private List<ArchiveDetails> details;

	private List<JsonArchive> archives;

	public Result() {

	}

	public Result(List<ArchiveDetails> details, List<JsonArchive> archives) {
		super();
		this.details = details;
		this.archives = archives;
	}

	public List<ArchiveDetails> getDetails() {
		return details;
	}

	public void setDetails(List<ArchiveDetails> details) {
		this.details = details;
	}

	public List<JsonArchive> getArchives() {
		return archives;
	}

	public void setArchives(List<JsonArchive> archives) {
		this.archives = archives;
	}

}
