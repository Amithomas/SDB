package com.equifax.dashboardportal.modifyserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.transaction.Transactional;

@Service
public class DashobardServiceImpl implements DashboardService {

	@Autowired
	private ServerGroupRepository sgRepository;

	@Autowired
	private UserInGroupRepository uigRepository;

	@Autowired
	private ServerGroupService sgService;

	@Autowired
	private UserInGroupService uigService;

	@Override
	@Transactional
	public ServerGroup createServerGroup(ServerGroup serverGroup) {

		return sgRepository.save(serverGroup);
	}

	@Override
	public ServerGroup updateServerGroup(String serverGroup) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserInGroup assignUserToServerGroup(UserInGroup userInGroup) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserInGroup removeUserFromServerGroup(String userId, String serverGroup) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserInGroup> findAllServerGroupByUserId(String userId) {

		return uigRepository.findByUserId(userId);
	}

	@Override
	@Transactional
	public ServerGroup delete(ServerGroup serverGroup) {
		return sgService.delete(serverGroup);
	}

	@Override
	public List<ServerGroup> findAllServerGroupByGroupName(String id) {
		return sgService.findByGroupName(id);
	}

	@Override
	public ServerGroup updateServerDetails(String servername) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserInGroup> findAll() {

		return uigRepository.findAll();
	}

	@Override
	public UserInGroup createUserInGroup(UserInGroup userInGroup) {
		UserInGroup userInGroupExisting = uigRepository.findByUserIdAndGroupName(userInGroup.getUserId(),
				userInGroup.getGroupName());

		if (userInGroupExisting == null) {
			return uigRepository.save(userInGroup);
		}
		return new UserInGroup();
	}

	@Override
	@Transactional
	public UserInGroup delete(UserInGroup userInGroup) {
		return uigRepository.delete(userInGroup);
	}

	@Override
	public List<ServerGroup> findAllServers() {
		return sgRepository.findAll();
	}

}
