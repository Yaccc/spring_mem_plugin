###spring_mem_plugin
>顾名思义是基于spring和memcached的一个插件,该插件目前实现了memcached的查询缓存，利用java的annotation方式和spring的AOP切面编程使得我们在业务层获得缓存数据非常的方便

##开始使用
首先你必须实现CacheSupport接口，我正好提供了一个memcachedDefault类给你
```java
package org.xie.framework.memcached.spring;

import org.xie.memcached.client.MemcachedCache;

public class MemcachedDefault implements CacheSupport {
	private MemcachedCache cached;
	public void setCached(MemcachedCache cached) {
		this.cached = cached;
	}

	@Override
	public boolean addCache(String key, Object value) {
		return cached.getCache().add(key, value);
	}

	@Override
	public boolean addCache(String key, Object value, long cacheTime) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteCache(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getCache(String key) {
		return 	cached.get(key);
	
	}

	@Override
	public boolean replaceCache(String key, Object value) {
		// TODO Auto-generated method stub
		return false;
	}

}
```
当然这只是一个简单的例子，你可以按照你自己要实现的memcached客户端方式去实现它
然后你需要配置applicationContext.xml.像下面这样
```xml
	
	<bean id="memDefault" class="org.xie.framework.memcached.spring.MemcachedDefault">
		<property name="cached" ref="memClient"></property><!-- 注入自己的客户端 -->
	</bean>
	<bean id="cacheAop" class="org.xie.framework.memcached.spring.GetCacheAop">
	<property name="cacheSupport" ref="memDefault"></property><!-- 注入实现 -->
	</bean>
	<aop:aspectj-autoproxy proxy-target-class="true"/> <!-- cglib代理必须要开启-->
	<aop:config>	
		<aop:aspect id="myAspect" ref="cacheAop">
		<!-- 配置切点。和环绕通知。method=“ProxyInvoke” -->
			<aop:pointcut id="businessService" expression="execution(* org.xie.service.*.*(..))" />
			<aop:around pointcut-ref="businessService" method="ProxyInvoke" />
		</aop:aspect>
	
	</aop:config>
```
###annotation使用
```java
@GetCacheVaule(key="id")//表示要缓存id
	public Object testaop(String id) {
		
		return userDao.getUsers();
	}
```
这个的意思是，传入的id是要缓存的键值，如果缓存中有则拿出来，没有则从数据库中拿，注意返回值要这样写哦！
目前只有这一个，其实已经差不多足够，以后有时间再写吧








