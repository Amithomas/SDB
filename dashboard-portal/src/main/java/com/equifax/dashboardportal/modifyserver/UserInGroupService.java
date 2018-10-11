package com.equifax.dashboardportal.modifyserver;

import java.util.List;

public interface UserInGroupService {

	UserInGroup create(UserInGroup user);

	boolean delete(String id);

    List<UserInGroup> findByUserId(String userId);
     
    UserInGroup update(UserInGroup userInGroup);

	boolean deleteByGroupName(String groupName);
    
    
}
