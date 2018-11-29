/**
 * 
 */
package com.cirb.archive.domain.vo;

import java.util.Date;

/**
 * @author Zkaoukab
 *
 */
public class SearchVO {

	private Date dateFrom;
	
	private Date dateTo;
	
	private Date timestamp;
	
	private String keyValue;
	
	private String transactionId;

	public SearchVO() {
		super();
	}

	public SearchVO(Date dateFrom, Date dateTo, Date timestamp, String keyValue, String transactionId) {
		super();
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.timestamp = timestamp;
		this.keyValue = keyValue;
		this.transactionId = transactionId;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
}
