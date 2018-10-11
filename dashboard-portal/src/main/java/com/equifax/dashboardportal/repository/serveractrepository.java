package com.equifax.dashboardportal.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;


import com.equifax.dashboardportal.model.Serveractions;
@RestResource(exported = false)
public interface serveractrepository extends JpaRepository<Serveractions, String>{

 public Serveractions findByip(String ip);
	

}