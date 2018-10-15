package com.equifax.dashboardportal.modifyserver;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import com.equifax.dashboardportal.model.RAMStatus;
import com.equifax.dashboardportal.model.CPUStatus;
import com.equifax.dashboardportal.model.DiskStatus;
import com.equifax.dashboardportal.model.HostStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
//import com.howtodoinjava.demo.model.ServerActions;
//import com.equifax.dashboardportal.model.ServerGroup;
import com.equifax.dashboardportal.model.Serveractions;
import com.equifax.dashboardportal.model.ServicesRunning;
import com.equifax.dashboardportal.model.ServiceStatus;
import com.equifax.dashboardportal.model.Service;
import com.equifax.dashboardportal.repository.HostCPURepository;
import com.equifax.dashboardportal.repository.HostRAMRepository;
//import com.howtodoinjava.demo.repository.ServerActionsRepository;
//import com.equifax.dashboardportal.repository.ServerGroupRepository;
import com.equifax.dashboardportal.repository.serveractrepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class StatusController {
	@Autowired
	private ServerGroupRepository repository;
	@Autowired
	private HostRAMRepository repository3;
	@Autowired
	private serveractrepository repository1;
	
	@Autowired
	private HostCPURepository repository2;
	
	@Autowired
	private ResourceLoader resourceLoader;
	@CrossOrigin
	@RequestMapping("/hoststatus")
    public HostStatus getstatus() throws IOException 
    {
		Resource resource = resourceLoader.getResource("classpath:status.txt");
		//To load a resource from a file system, you use the file prefix.
		//To load a resource from the classpath, you use the classpath prefix.
		//List<String> lines = Files.readAllLines(Paths.get(resource.getURI()),
          //      StandardCharsets.UTF_8);
		String statecode = null;
		String statecode1 = null;
		String statecode2 = null;
		Double up = null;
		Double down = null;
		InputStream in = resource.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		for(int count=0; count<90;count++)
		{
			if(count==69)
			{
			statecode=reader.readLine();
			}
			if(count==85)
			{
			statecode1=reader.readLine();
			statecode2=reader.readLine();
			break;
			}
			reader.readLine();	
		}
		
		HostStatus status= new HostStatus();
		up=Double.parseDouble(statecode1.substring(21));
		down=Double.parseDouble(statecode2.substring(23));
		status.setLast_time_up(up);
		status.setLast_time_down(down);
		if (statecode.equals("        current_state=0"))
		{
			status.setHoststatus("OK");
		}
		else if (statecode.equals("        current_state=1"))
		{
			status.setHoststatus("CRITICAL");	
		}
		else if (statecode.equals("        current_state=2"))
		{
			status.setHoststatus("WARNING");	
		}
		else {
			status.setHoststatus("Unknown Status");	
		}
		return status;
	}
	@CrossOrigin
	@RequestMapping("/diskstatus")
    public DiskStatus getdiskstatus() throws IOException 
    {
		String[] command = { "/usr/local/nagios/libexec/check_disk","-w","20%","-c","10%"};
            Process process = Runtime.getRuntime().exec(command);
 BufferedReader reader = new BufferedReader(new InputStreamReader(
                process.getInputStream()));
                String state= null;
                state=reader.readLine();
		String[] splitStr = state.trim().split("\\s+");
		DiskStatus disk= new DiskStatus();
		disk.setAvailable(Double.parseDouble(splitStr[6]));
		disk.setPercent(Double.parseDouble(splitStr[8].substring(1,6)));
		Double size;
		Double used;
		size= Double.parseDouble(splitStr[6])/((Double.parseDouble(splitStr[8].substring(1,6))/100));
		disk.setSize(size);
		used= size-(Double.parseDouble(splitStr[6]));
		disk.setUsed(used);
		return disk;
	}
	@CrossOrigin
	@RequestMapping(value="/cpustatus", method = RequestMethod.GET)
    public CPUStatus getcpustatus() throws IOException 
    {
		 String[] command = { "/usr/local/nagios/libexec/check_cpu_stats.sh"};
            Process process = Runtime.getRuntime().exec(command);
 BufferedReader reader = new BufferedReader(new InputStreamReader(
                process.getInputStream()));
		String state= null;
		state=reader.readLine();
		String[] splitStr = state.trim().split("\\s+");
		CPUStatus cpu1= repository2.findBygroupName("servergroup#1");
		cpu1.setCPUusage(100-(Double.parseDouble(splitStr[6].substring(5,10))));
		String []stat= cpu1.getDATA();
		String temp = stat[11];
		String v;
		stat[11]=cpu1.getCPUusage().toString();
		for(int i=10;i>=0;i--)
		{
			v=stat[i];
			stat[i]=temp;
			temp=v;
			
			
		}
		cpu1.setDATA(stat);
		repository2.save(cpu1);
		return cpu1;
    }
	@CrossOrigin

       @RequestMapping(value="/ramstatus", method = RequestMethod.GET)

    public RAMStatus getramstatus() throws IOException

    {             String[] command = { "/usr/local/nagios/libexec/check_mem.pl","-f","-w","20%","-c","10%"};
            Process process = Runtime.getRuntime().exec(command);
 BufferedReader reader = new BufferedReader(new InputStreamReader(
                process.getInputStream()));
                String state= null;

              System.out.print(state);
              

              state=reader.readLine();

              String[] splitStr = state.trim().split("\\s+");

              RAMStatus ram1= repository3.findBygroupName("servergroup#1");

              //System.out.print(state);

              ram1.setRAMUsage(100-(Double.parseDouble(splitStr[2].substring(0,4))));

              
              String []stat= ram1.getDATA();

              String temp = stat[11];

              String v;

              stat[11]=ram1.getRAMUsage().toString();

              for(int i=10;i>=0;i--)

              {

                     v=stat[i];

                     stat[i]=temp;

                     temp=v;

                    

                    

              }

              ram1.setDATA(stat);

              repository3.save(ram1);

              return ram1;

    }
	/*@RequestMapping(value="/services", method = RequestMethod.GET)
    public List<ServicesRunning> getservices() throws IOException {
		Resource resource = resourceLoader.getResource("classpath:netstat.txt");
		List<String> lines = Files.readAllLines(Paths.get(resource.getURI()),
		                StandardCharsets.UTF_8);
		String state = null;
		//System.out.print(lines.get(2));
		//ServicesRunning single = new ServicesRunning();
		List<ServicesRunning> service= new ArrayList<ServicesRunning>();
		for(int count=2; count< lines.size(); count++ )
		{
			ServicesRunning single = new ServicesRunning();
			String[] word= lines.get(count).split("\\s+");
			single.setProtocol(word[0]);
			single.setAddress(word[3]);
			single.setState(word[5]);
			single.setPIDProgram_name(word[6]);
			service.add(single);
		}
		
		return service;
	}*/
	
	@RequestMapping(value="/getallservers", method = RequestMethod.GET)
    public List<ServicesRunning> get1services() throws IOException {
		String[] command = { "netstat","-a" ,"-n","-o"};
	    Process process = Runtime.getRuntime().exec(command);
	    BufferedReader reader = new BufferedReader(new InputStreamReader(
	        process.getInputStream()));
	    List<ServicesRunning> service= new ArrayList<ServicesRunning>();
	    String s;
	    reader.readLine();
	    reader.readLine();
	    reader.readLine();
	    reader.readLine();
	    while ((s = reader.readLine()) != null) {
	      //System.out.println(s);
	      String[] word= s.split("\\s+");
	      //System.out.println(word.length);
	      if(word.length>5) {
	      ServicesRunning single = new ServicesRunning();
	      //single.setProtocol(word[1]);
	      String[] ip= word[2].split(":");
			single.setIp(ip[0]);
			single.setPort(ip[1]);
			single.setState(word[4]);
			//single.setPID(word[5]);
			
			service.add(single);
	      }

	}
	return service;
	
	}
	@RequestMapping(value="/getserverstatus", method = RequestMethod.POST)
	public List<ServicesRunning> Serviecstatus (@RequestBody ServiceStatus service) throws IOException {
		System.out.print(service.getPorts()[0]);
		List<ServicesRunning> service1= new ArrayList<ServicesRunning>();
		for(int count1 =0; count1 < service.getPorts().length; count1++)
		{
			ServicesRunning single = new ServicesRunning();
			String[] ip2=service.getPorts()[count1].split(":");
			single.setIp(ip2[0]);
			single.setPort(ip2[1]);
			single.setState("STOPPED");
			single.setServertype("tomcat");
		
			String[] command = { "netstat","-a" ,"-n","-o"};
		    Process process = Runtime.getRuntime().exec(command);
		    BufferedReader reader = new BufferedReader(new InputStreamReader(
		        process.getInputStream()));
		    String s;
		    reader.readLine();
		    reader.readLine();
		    reader.readLine();
		    reader.readLine();
		    while ((s = reader.readLine()) != null) {
			      //System.out.println(s);
			      String[] word= s.split("\\s+");
			      String[] ip= word[2].split(":");
			      //System.out.println(ip[1]);
			      if(word.length>5) {
			    	String[] ip1 = service.getPorts()[count1].split(":");
			      if (ip[1].equals(ip1[1])&& ip[0].equals(ip1[0]))
			      {
			    	  single.setState(word[4]); 
			    	  break;
			      }
		    
		}}
		    service1.add(single);
		    }
		
		return service1;
		
		
	}
/*
	@RequestMapping(value="/startserver", method = RequestMethod.POST)
    public String servicestart(@RequestBody Serveractions sg) throws IOException 
    {	
		
		String command = repository1.findByip(sg.getIp()).getStart();
	    Process process = Runtime.getRuntime().exec(command);
	    BufferedReader reader = new BufferedReader(new InputStreamReader(
	        process.getInputStream()));
	    String S= reader.readLine();
	    return S;
	    
    }
	@RequestMapping(value="/view", method = RequestMethod.GET)
    public List<Serveractions> seeall() throws IOException 
    
    {	
    return repository1.findAll();
    }
	
	@RequestMapping(value="/stopserver", method = RequestMethod.POST)
    public String servicestopt(@RequestBody Serveractions sg) throws IOException 
    {	
		
		String command = repository1.findByip(sg.getIp()).getStop();
	    Process process = Runtime.getRuntime().exec(command);
	    BufferedReader reader = new BufferedReader(new InputStreamReader(
	        process.getInputStream()));
	    String S= reader.readLine();
	    return S;
	    
    }*/
	@RequestMapping(value="/insert", method = RequestMethod.GET)
       public RAMStatus Insert1 ()
      
       {     
              RAMStatus sg1 =new RAMStatus();
              sg1.setGroupName("servergroup#1");
              sg1.setRAMUsage(49.9998);
              sg1.setIp("1.1.1.1");
              String [] d = {"54.72",
                      "54.72",
                      "54.72",
                      "54.72",
                      "54.72",
                      "54.72",
                      "54.72",
                      "54.72",
                      "54.72",
                      "54.72",
                      "54.72",
                      "54.72"};
 
              sg1.setDATA(d);
              String [] l= {
                           "5m",
                      "10m",
                      "15m",
                      "20m",
                      "25m",
                      "30m",
                      "35m",
                      "40m",
                      "45m",
                      "50m",
                      "55m",
                      "60m"
 				 };
		sg1.setLabels(l);
return repository3.save(sg1);
}
/*@RequestMapping(value="/viewall", method = RequestMethod.GET)
	public List<ServerGroup> Insert () throws IOException
	{	
		List<ServerGroup> nsg= new ArrayList<ServerGroup>();
		nsg=repository.findAll();
		Resource resource = resourceLoader.getResource("classpath:status.txt");
		String statecode = null;
		InputStream in = resource.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		for(int count=0; count<90;count++)
		{
			if(count==69)
			{
			statecode=reader.readLine();
			break;
			}
			reader.readLine();
		}
		System.out.print(statecode);
		if (statecode.equals("        current_state=0"))
		{
			nsg.get(0).setStatus("OK");
		}
		else if (statecode.equals("        current_state=1"))
		{
			nsg.get(0).setStatus("CRITICAL");	
		}
		else if (statecode.equals("        current_state=2"))
		{
			nsg.get(0).setStatus("WARNING");	
		}
		else {
			nsg.get(0).setStatus("Unknown Status");	
		}
		return  nsg;	
	}*/
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(value="/services", method = RequestMethod.GET)
	public List<Service> service_list () throws IOException
	{	
		String[] command = {"curl","--noproxy","*","-v","-u","my_user:my_pass","http://127.0.0.1:8080/manager/text/list" };
	    Process process = Runtime.getRuntime().exec(command);
	    BufferedReader reader = new BufferedReader(new InputStreamReader(
	        process.getInputStream()));
	    String s;
	    List<Service> servicelist= new ArrayList<Service>();
	    reader.readLine();
	    while ((s = reader.readLine()) != null) {
	    	//System.out.println(s);
	    	Service service=new Service();
	    	String[] parts= s.split(":");
	    	service.setServicename(parts[0]);
	    	service.setStatus(parts[1]);
	    	service.setServicetype("Web Service");
	    	service.setServertype("Tomcat");
	    	servicelist.add(service);
	    }
		
		return servicelist;
	}
	@CrossOrigin
	@RequestMapping(value="/serviceslist", method = RequestMethod.POST)
	public List<Service> service_list1 (ServerGroup sg) throws IOException
	{	
		/*String s1= sg.getIp();
	String template="http://:8070/manager/text/stop?path=%s";
	String path=String.format(template,s1);
	*/
		
		String[] command = {"D:\\curl-7.61.1-win64-mingw\\bin\\curl","-v","-u","tomcat:s3cret","http://127.0.0.1:8070/manager/text/list" };
	    Process process = Runtime.getRuntime().exec(command);
	    BufferedReader reader = new BufferedReader(new InputStreamReader(
	        process.getInputStream()));
	    String s;
	    List<Service> servicelist= new ArrayList<Service>();
	    reader.readLine();
	    while ((s = reader.readLine()) != null) {
	    	//System.out.println(s);
	    	Service service=new Service();
	    	String[] parts= s.split(":");
	    	service.setServicename(parts[0]);
	    	service.setStatus(parts[1]);
	    	service.setServertype("Tomcat");
	    	servicelist.add(service);
	    }
		
		return servicelist;
	}
	@RequestMapping(value="/servicestatus", method = RequestMethod.POST)
	public Service service_status (@RequestBody Service st) throws IOException
	{	
		String[] command = {"" };
	    Process process = Runtime.getRuntime().exec(command);
	    BufferedReader reader = new BufferedReader(new InputStreamReader(
	        process.getInputStream()));
	    String s;
	    Service service=new Service();
	    
	    reader.readLine();
	    while ((s = reader.readLine()) != null) {
	    	
	    	String[] parts= s.split(":");
	    	if (st.getServicename().equals(parts[0]))
	    	{
	    	service.setServicename(parts[0]);
	    	service.setStatus(parts[1]);
	    	service.setServertype("Tomcat");
	    	break;
	    	}
	    	
	    		
	    	
	    }
		
		return service;
	}
/*
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(value="/stopservice", method = RequestMethod.POST)
	public boolean stopservice (@RequestBody Service service) throws IOException
	{	
		String s1= service.getServicename();
		String template="http://127.0.0.1:8080/manager/text/stop?path=%s";
		String path=String.format(template,s1);
	
		String[] command = {"curl","--noproxy","*","-v","-u","my_user:my_pass",path };
	    Process process = Runtime.getRuntime().exec(command);
	    BufferedReader reader = new BufferedReader(new InputStreamReader(
	        process.getInputStream()));
	    String s=reader.readLine();
	    String [] stat= s.split("\\s+");
		   System.out.print(stat[0]);
		   if(stat[0].equals("OK"))
		   {
			   return true;
		   }
		   else {
			   return false;
		   }
	    
	   
	    
		
	}
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(value="/startservice", method = RequestMethod.POST)
	public boolean startservice (@RequestBody Service service) throws IOException
	{	
		String s1= service.getServicename();
		String template="http://127.0.0.1:8080/manager/text/start?path=%s";
		String path=String.format(template,s1);
	
		String[] command = {"curl","--noproxy","*","-v","-u","my_user:my_pass",path };
	    Process process = Runtime.getRuntime().exec(command);
	    BufferedReader reader = new BufferedReader(new InputStreamReader(
	        process.getInputStream()));
	    String s=reader.readLine();
	    String [] stat= s.split("\\s+");
	   System.out.print(stat[0]);
	   if(stat[0].equals("OK"))
	   {
		   return true;
	   }
	   else {
		   return false;
	   }
		   
	   
	    
	    		
	}*/
	@CrossOrigin(origins = "*", allowedHeaders = "*")

       @RequestMapping(value="/stopservice", method = RequestMethod.GET)

       public boolean stopservice (@RequestParam ("sname") String sname ) throws IOException

             

       {     

             

              String template="http://127.0.0.1:8080/manager/text/stop?path=%s";

              String path=String.format(template,sname);

      

              String[] command = {"curl","--noproxy","*","-v","-u","my_user:my_pass",path };

           Process process = Runtime.getRuntime().exec(command);

           BufferedReader reader = new BufferedReader(new InputStreamReader(

               process.getInputStream()));

           String s=reader.readLine();

           String [] stat= s.split("\\s+");

                 System.out.print(stat[0]);

                 if(stat[0].equals("OK"))

                 {

                        return true;

                 }

                 else {

                        return false;

                 }

          

          

           

             

       }

       @CrossOrigin(origins = "*", allowedHeaders = "*")

       @RequestMapping(value="/startservice", method = RequestMethod.GET)

       public boolean startservice (@RequestParam ("sname") String sname) throws IOException

       {     

             

              String template="http://127.0.0.1:8080/manager/text/start?path=%s";

              String path=String.format(template,sname);

      

              String[] command = {"curl","--noproxy","*","-v","-u","my_user:my_pass",path };

           Process process = Runtime.getRuntime().exec(command);

           BufferedReader reader = new BufferedReader(new InputStreamReader(

               process.getInputStream()));

           String s=reader.readLine();

           String [] stat= s.split("\\s+");

          System.out.print(stat[0]);

          if(stat[0].equals("OK"))

          {

                 return true;

          }

          else {

                 return false;

          }

                

          

           

                    

       }

 
}
