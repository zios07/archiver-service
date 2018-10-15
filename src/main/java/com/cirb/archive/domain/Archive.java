package com.cirb.archive.domain;

import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(collection = "archives")
public class Archive {

	@Id
	@Field
	private Long id;

	@Field
	private Date date;

	@Field
	private Byte[] content;
	
	@Field
	private String extension;
	
	@Field
	private String fileName;

	public Archive(Date date, Byte[] content, String extension, String fileName) {
		super();
		this.date = date;
		this.content = content;
		this.extension = extension;
		this.fileName = fileName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Byte[] getContent() {
		return content;
	}

	public void setContent(Byte[] content) {
		this.content = content;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	
}
