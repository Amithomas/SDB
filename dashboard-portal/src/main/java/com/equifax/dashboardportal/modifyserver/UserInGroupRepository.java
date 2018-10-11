package com.equifax.dashboardportal.modifyserver;

import java.util.List;

import org.springframework.data.repository.Repository;

public interface UserInGroupRepository extends Repository<UserInGroup, Integer> {

	UserInGroup delete(UserInGroup userInGroup);

	void deleteByUserId(String userId);
	
	void deleteByGroupName(String groupName);

	List<UserInGroup> findByUserId(String userId);

	UserInGroup save(UserInGroup userInGroup);
	
	UserInGroup findByUserIdAndGroupName(String userId,String groupName);
	
	UserInGroup deleteByUserIdAndGroupName(String userId,String groupName);
	
	List<UserInGroup> findAll();

}
