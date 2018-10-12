package com.equifax.dashboardportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;


import com.equifax.dashboardportal.model.RAMStatus;

@RestResource(exported = false)
public interface HostRAMRepository extends JpaRepository<RAMStatus, String> {
         public RAMStatus findBygroupName(String groupName);
        }

