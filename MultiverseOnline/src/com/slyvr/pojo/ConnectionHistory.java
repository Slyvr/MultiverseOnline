package com.slyvr.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Entity;

@Entity
@javax.persistence.Entity(name="ConnectionHistory")
@Table(name="CONNECTION_HISTORY")
public class ConnectionHistory {

	@Id @GeneratedValue
	@Column(name="CONNECTION_HISTORY_ID")
	private Integer connectionHistoryId;
	@Column(name="CONNECTION_IP")
	private String connectionIp;
	@Column(name="CONNECTION_DATE")
	private Date connectionDate;
	
	public ConnectionHistory(){
		
	}

	public Integer getConnectionHistoryId() {
		return connectionHistoryId;
	}

	public void setConnectionHistoryId(Integer connectionHistoryId) {
		this.connectionHistoryId = connectionHistoryId;
	}

	public String getConnectionIp() {
		return connectionIp;
	}

	public void setConnectionIp(String connectionIp) {
		this.connectionIp = connectionIp;
	}

	public Date getConnectionDate() {
		return connectionDate;
	}

	public void setConnectionDate(Date connectionDate) {
		this.connectionDate = connectionDate;
	}
	
}
