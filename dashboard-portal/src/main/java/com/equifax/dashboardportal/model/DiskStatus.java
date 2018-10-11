package com.equifax.dashboardportal.model;

public class DiskStatus {
	private String servername;
	private String servicename;
	private String Address;
	private String ip;
	private String servertype;
	private String groupName;
	
	private int filesystem;
	private double size;
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
	private double used;
	private double available;
	private double percent;
	public int getFilesystem() {
		return filesystem;
	}
	public void setFilesystem(int filesystem) {
		this.filesystem = filesystem;
	}
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	public double getUsed() {
		return used;
	}
	public void setUsed(double used) {
		this.used = used;
	}
	public double getAvailable() {
		return available;
	}
	public void setAvailable(double available) {
		this.available = available;
	}
	public double getPercent() {
		return percent;
	}
	public void setPercent(double percent) {
		this.percent = percent;
	}

}
