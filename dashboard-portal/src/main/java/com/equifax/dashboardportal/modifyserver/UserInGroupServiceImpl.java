package com.equifax.dashboardportal.modifyserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.transaction.Transactional;

@Service
public class UserInGroupServiceImpl implements UserInGroupService {

	@Autowired
	private UserInGroupRepository repository;

	@Override
	public UserInGroup create(UserInGroup user) {

		UserInGroup existingUser = repository.findByUserIdAndGroupName(user.getUserId(), user.getGroupName());
		if (existingUser == null) {
			return repository.save(user);
		} else {
			return existingUser;
		}

	}

	@Override
	@Transactional
	public boolean delete(String id) {
		List<UserInGroup> userList = repository.findByUserId(id);
		boolean flag = false;

		if (userList != null && !userList.isEmpty()) {
			for (UserInGroup u : userList)
				repository.delete(u);
			flag = true;

		}

		return flag;
	}

	@Override
	@Transactional
	public boolean deleteByGroupName(String groupName) {
		repository.deleteByGroupName(groupName);
		return true;
	}

	@Override
	public List<UserInGroup> findByUserId(String userId) {
		return repository.findByUserId(userId);
	}

	@Override
	@Transactional
	public UserInGroup update(UserInGroup user) {

		return repository.save(user);
	}

}
