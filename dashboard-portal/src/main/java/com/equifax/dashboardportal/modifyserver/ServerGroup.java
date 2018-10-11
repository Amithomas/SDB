package com.equifax.dashboardportal.modifyserver;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ServerGroup")
public class ServerGroup {

	@Column
	@Id
	private String serverId;
	@Column
	private String servername;
	@Column
	private String servicename;
	@Column
	private String ip;
	@Column
	private String servertype;
	@Column
	private String groupName;
	@Column
	private String port;

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getServername() {
		return servername;
	}

	public void setServername(String servername) {
		this.servername = servername;
	}

	public String getServicename() {
		return servicename;
	}

	public void setServicename(String servicename) {
		this.servicename = servicename;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
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

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

}
