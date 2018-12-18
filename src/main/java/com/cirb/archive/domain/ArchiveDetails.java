/**
 * 
 */
package com.cirb.archive.domain;

/**
 * @author Zkaoukab
 *
 */
public class ArchiveDetails {
	
	private String id;
	
	private String fileName;

	public ArchiveDetails(String id, String fileName) {
		super();
		this.id = id;
		this.fileName = fileName;
	}

	public ArchiveDetails() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
