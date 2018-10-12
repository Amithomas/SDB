package com.equifax.dashboardportal.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.equifax.dashboardportal.model.CPUStatus;

@RestResource(exported = false)
public interface HostCPURepository extends JpaRepository<CPUStatus, String>{
	
 public CPUStatus findBygroupName(String groupName);
}
