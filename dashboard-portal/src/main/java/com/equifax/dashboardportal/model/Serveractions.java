package com.equifax.dashboardportal.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "Serveract")
public class Serveractions {
	@Column
	@Id
	private String ip;
	@Column
	private String servername;
	@Column
	private String start;
	@Column
	private String stop;
	@Column
	private String servertype;
	@Column
	private String groupName;
	
	@Column
	private String status;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getServername() {
		return servername;
	}

	public void setServername(String servername) {
		this.servername = servername;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getStop() {
		return stop;
	}

	public void setStop(String stop) {
		this.stop = stop;
	}

	public String getServertype() {
		return servertype;
	}

	public void setServertype(String servertype) {
		this.servertype = servertype;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	

}
