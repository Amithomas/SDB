package com.equifax.dashboardportal;

import java.util.List;

import javax.persistence.GeneratedValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.equifax.dashboardportal.modifyserver.ServerGroup;
import com.equifax.dashboardportal.modifyserver.ServerGroupRepository;
import com.equifax.dashboardportal.modifyserver.UserInGroup;
import com.equifax.dashboardportal.modifyserver.UserInGroupService;

@SpringBootApplication
public class DashboardPortalApplication //extends SpringBootServletInitializer  {
implements CommandLineRunner{

	@Autowired
	private UserInGroupService uigRepository;
	
	
	@Autowired
	private ServerGroupRepository sgRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(DashboardPortalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		UserInGroup uigroup =  new UserInGroup();
        uigroup.setUserId("axm283");
		uigroup.setGroupName("servergroupC");
		uigRepository.create(uigroup);
		UserInGroup uigroup1 =  new UserInGroup();
        uigroup1.setUserId("axm283");
		uigroup1.setGroupName("servergroupB");
		uigRepository.create(uigroup1);
		UserInGroup uigroup2 =  new UserInGroup();
        uigroup2.setUserId("axm283");
		uigroup2.setGroupName("servergroupA");
		uigRepository.create(uigroup2);
//		
//		 uigroup.setUserId("axm283");
//			uigroup.setGroupId("server_D");
//			uigRepository.create(uigroup);
//		
	//	uigRepository.update(uigroup);
		
	//	uigRepository.delete("axm283");
		
		List<UserInGroup> list = uigRepository.findByUserId("axm283");
		for (UserInGroup u : list) {
			System.out.println(u.getId() +" "+u.getUserId()+" " + u.getGroupName());
		}
		
		ServerGroup sg = new ServerGroup();
		sg.setGroupName("servergroupA");
		sg.setServername("servernameAA");
		sg.setServerId("server_C");
		sg.setIp("172.2.2.3");
		sg.setPort("9405");
		sgRepository.save(sg);
//		
//		ServerGroup sg1 = new ServerGroup();
//		sg1.setGroupName("servergroupA");
//		sg1.setServername("servernameA");
//		sg1.setServerId("server_A");
//		sg1.setIp("172.2.2.1");
//		sg1.setPort("9406");
//		sgRepository.save(sg1);
//		
//		ServerGroup sg2 = new ServerGroup();
//		sg2.setGroupName("servergroupA");
//		sg2.setServername("servernameAA");
//		sg2.setServerId("server_B");
//		sg2.setIp("172.2.2.2");
//		sg2.setPort("8080");
//		sgRepository.save(sg2);
		
		//uigRepository.deleteByGroupId("server_B");
		
		List<ServerGroup> list1 = sgRepository.findAll();
		for (ServerGroup u : list1) {
			System.out.println(u.getGroupName() +" "+u.getServerId()+" " + u.getServername());
		}
		
				
	}
	
	
	
//	@Override
//	  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//	    return application.sources(DashboardPortalApplication.class);
//	  }
//
//	  public static void main(String[] args) {
//	    SpringApplication.run(DashboardPortalApplication.class, args);
//	  }
}
