package org.xie.service;

import org.memcached.xiezhaodong.test.Employee;
import org.xiezhaodong.spring.annotation.GetCacheVaule;

public class Service_A_impl implements AService {

	@Override
	
	public Employee before() {
		System.out.println("A____执行前");
		return null;
	
	}
	
	@Override
	public Integer after() {
		System.out.println("A____执行后");
		return 0;
	}

	@Override
	@GetCacheVaule(key="id")//表示要缓存id
	public Object testaop(String id) {
		
		return "sssss";
	}


}
