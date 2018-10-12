package com.equifax.dashboardportal.modifyserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200", exposedHeaders = "Access-Control-Allow-Origin")
@RestController
public class DashboardController {

	@Autowired
	private DashboardService dashboardService;

		
	@GetMapping("/getServers")
	public List<ServerGroup> findAllServers() {
		return dashboardService.findAllServers();
	}

	@GetMapping("/serverlist/{userid}")
	public HashMap<String, List<ServerGroup>> findAllServers(@PathVariable("userid") String userId) {
		List<UserInGroup> userinGroupList = dashboardService.findAllServerGroupByUserId(userId);
		List<ServerGroup> servers = new ArrayList<ServerGroup>();
		HashMap<String, List<ServerGroup>> hashMap = new HashMap<String, List<ServerGroup>>();

		if (userinGroupList != null && !userinGroupList.isEmpty()) {
			for (UserInGroup userinGroup : userinGroupList) {
				List<ServerGroup> sg = dashboardService.findAllServerGroupByGroupName(userinGroup.getGroupName());
				if (sg != null && !sg.isEmpty()) {

					for (ServerGroup s : sg) {
						if (!hashMap.containsKey(s.getGroupName())) {
							List<ServerGroup> list = new ArrayList<ServerGroup>();
							list.add(s);
							hashMap.put(s.getGroupName(), list);
						} else {
							hashMap.get(s.getGroupName()).add(s);
						}

					}

				}

			}
		}

		return hashMap;
	}

	@GetMapping("/userIds")
	public List<UserInGroup> findAllUserIdAndGroupNames() {
		return dashboardService.findAll();

	}

	@PutMapping("/updateuseringroup")
	public UserInGroup updateServer(@RequestBody UserInGroup userInGroup) {
		return dashboardService.createUserInGroup(userInGroup);
	}

	@PostMapping("/adduseringroup")
	public UserInGroup create(@RequestBody UserInGroup userInGroup) {
		return dashboardService.createUserInGroup(userInGroup);
	}

	@PutMapping("/updateserver")
	public ServerGroup updateServer(@RequestBody ServerGroup serverGroup) {
		return dashboardService.createServerGroup(serverGroup);
	}

	@PostMapping("/addserver")
	public ServerGroup createServer(@RequestBody ServerGroup serverGroup) {
		return dashboardService.createServerGroup(serverGroup);
	}

	@PostMapping("/deleteserver")
	public boolean deleteServerId(@RequestBody ServerGroup serverGroup) {
		dashboardService.delete(serverGroup);
		return true;
	}

	@PostMapping("/deleteuseridgroupname")
	public boolean deleteUserIdAndGroupName(@RequestBody UserInGroup userInGroup) {
		dashboardService.delete(userInGroup);
		return true;
	}

}
