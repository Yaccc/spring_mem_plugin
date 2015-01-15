package org.xie.service;

import org.memcached.xiezhaodong.test.Employee;

public interface AService {
	
	public Employee before();
	public Integer after();
	//public Employee around();
	public Object testaop(String id);
	
	

}
