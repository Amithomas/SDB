package com.equifax.dashboardportal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HostRAM")


public class RAMStatus {
	@Column
	private Double RAMUsage;
	@Column
	private String[] DATA;
	
	public Double getRAMUsage() {
		return RAMUsage;
	}
	public void setRAMUsage(Double rAMUsage) {
		RAMUsage = rAMUsage;
	}
	public String[] getDATA() {
		return DATA;
	}
	public void setDATA(String[] dATA) {
		DATA = dATA;
	}
	public String[] getLabels() {
		return labels;
	}
	public void setLabels(String[] labels) {
		this.labels = labels;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	@Column
	private String[] labels;
	

	@Column
	private String ip;
	@Id
	@Column
	private String groupName;

}

