package com.equifax.dashboardportal.modifyserver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerGroupServiceImpl implements ServerGroupService {

	@Autowired
	private ServerGroupRepository repository;

	@Override
	public ServerGroup create(ServerGroup user) {
		return repository.save(user);
	}

	@Override
	public ServerGroup delete(ServerGroup serverGroup) {
		ServerGroup server = repository.findByServerId(serverGroup.getServerId());
		if (server != null) {
			repository.delete(server);
		}
		return server;
	}

	@Override
	public ServerGroup update(ServerGroup user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ServerGroup> findByGroupName(String groupName) {
		return repository.findByGroupName(groupName);
	}

}
