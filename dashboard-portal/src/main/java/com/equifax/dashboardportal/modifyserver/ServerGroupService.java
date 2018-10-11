package com.equifax.dashboardportal.modifyserver;

import java.util.List;

public interface ServerGroupService {

	ServerGroup create(ServerGroup user);

	ServerGroup delete(ServerGroup serverGroup);
    
    List<ServerGroup> findByGroupName(String serverId);

     ServerGroup update(ServerGroup user);
}
