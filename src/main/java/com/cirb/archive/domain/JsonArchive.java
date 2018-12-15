package com.cirb.archive.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.io.Serializable;
import java.util.Date;

public class JsonArchive implements Serializable {

	@JsonInclude(Include.NON_NULL)
	private Long id;

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
