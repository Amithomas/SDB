package com.equifax.dashboardportal.modifyserver;

import java.util.List;

import org.springframework.data.repository.Repository;

public interface ServerGroupRepository extends Repository<ServerGroup, Integer> {

	void delete(ServerGroup serverGroup);

	List<ServerGroup> findAll();
	
	ServerGroup findByServerId(String serverId);
	
	List<ServerGroup> findByGroupName(String groupName);

	ServerGroup save(ServerGroup server);



}
