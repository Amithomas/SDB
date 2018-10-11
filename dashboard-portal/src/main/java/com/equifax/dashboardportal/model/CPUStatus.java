package com.equifax.dashboardportal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HostCPU")

public class CPUStatus {
	@Column
	private Double CPUusage;
	@Column
	private String[] DATA;
	
	@Column
	private String[] labels;
	

	public String[] getLabels() {
		return labels;
	}

	public void setLabels(String[] labels) {
		this.labels = labels;
	}

	public String[] getDATA() {
		return DATA;
	}

	public void setDATA(String[] dATA) {
		DATA = dATA;
	}
	
	
	@Column
	private String ip;
	@Id
	@Column
	private String groupName;


	public Double getCPUusage() {
		return CPUusage;
	}

	public void setCPUusage(Double cPUusage) {
		CPUusage = cPUusage;
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

	

}
