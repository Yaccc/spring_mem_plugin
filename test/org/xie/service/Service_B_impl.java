package org.xie.service;

import org.memcached.xiezhaodong.test.Employee;

public class Service_B_impl implements AService {

	@Override
	public Employee before() {
		System.out.println("B____执行前");
		
		Employee employee=new Employee();
		employee.setCompanyName("a zhong");
		employee.setDeptName("aaaa");
		employee.setEmpName("aaade");
		
		
		return employee;
	}

	@Override
	public Integer after() {
		System.out.println("B____执行后");return 0;
	}

	@Override
	public Object testaop(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
