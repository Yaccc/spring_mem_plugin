package test;

import org.memcached.xiezhaodong.test.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.xie.memcached.client.MemcachedCache;
import org.xie.service.AService;
import org.xie.service.Service_A_impl;

import com.alisoft.xplatform.asf.cache.IMemcachedCache;

public class Main{
	
	public static void main(String[] args) {
		FileSystemXmlApplicationContext applicationContext=new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
		
//		MemcachedCache cache=(MemcachedCache) applicationContext.getBean("memClient");
//		IMemcachedCache cc=cache.getCache();
		
	//cache.put("1111", object)''
	//	System.out.println(cache.get("emp1"));
		AService a=(AService) applicationContext.getBean("aimpl");
	//	AService B=(AService) applicationContext.getBean("bimpl");
		System.out.println((String)a.testaop("test2"));
	//	B.before();
//		System.out.println(emp.getCompanyName());
		
		
	}

}
