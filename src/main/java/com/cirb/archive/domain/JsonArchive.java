package com.cirb.archive.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class JsonArchive implements Serializable {

	private static final long serialVersionUID = 5273127257463340020L;

	@JsonInclude(Include.NON_NULL)
	private String id;

	private Date date;

	private ArchiveMetadata consumer;

	private ArchiveMetadata provider;

	public JsonArchive() {
		super();
	}

	public JsonArchive(Date date, ArchiveMetadata consumer, ArchiveMetadata provider) {
		super();
		this.date = date;
		this.consumer = consumer;
		this.provider = provider;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ArchiveMetadata getConsumer() {
		return consumer;
	}

	public void setConsumer(ArchiveMetadata consumer) {
		this.consumer = consumer;
	}

	public ArchiveMetadata getProvider() {
		return provider;
	}

	public void setProvider(ArchiveMetadata provider) {
		this.provider = provider;
	}

}
