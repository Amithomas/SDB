package com.equifax.dashboardportal.modifyserver;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface DashboardService {

	ServerGroup createServerGroup(ServerGroup serverGroup);

	
	List<ServerGroup> findAllServerGroupByGroupName(String id);

	List<UserInGroup> findAllServerGroupByUserId(String userId);

	ServerGroup updateServerDetails(String servername);

	ServerGroup updateServerGroup(String serverGroup);

	UserInGroup assignUserToServerGroup(UserInGroup userInGroup);

	UserInGroup removeUserFromServerGroup(String userId, String serverGroup);

	List<UserInGroup> findAll();
	
	List<ServerGroup> findAllServers();

	UserInGroup createUserInGroup(UserInGroup userInGroup);

	UserInGroup delete(UserInGroup userInGroup);

	ServerGroup delete(ServerGroup serverGroup);

}
