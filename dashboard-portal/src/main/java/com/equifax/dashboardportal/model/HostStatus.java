package com.equifax.dashboardportal.model;

public class HostStatus {

	
	
	private String servername;
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
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
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
	private String servicename;
	private String Address;
	private String ip;
	private String servertype;
	private String groupName;
	private String hoststatus;
	private Double last_time_up;
	private Double last_time_down;
	public String getHoststatus() {
		return hoststatus;
	}
	public void setHoststatus(String hoststatus) {
		this.hoststatus = hoststatus;
	}
	public Double getLast_time_up() {
		return last_time_up;
	}
	public void setLast_time_up(Double last_time_up) {
		this.last_time_up = last_time_up;
	}
	public Double getLast_time_down() {
		return last_time_down;
	}
	public void setLast_time_down(Double last_time_down) {
		this.last_time_down = last_time_down;
	}
	
	
	
	
	
}
