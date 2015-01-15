package org.xie.memcached.client;

import com.alisoft.xplatform.asf.cache.ICacheManager;
import com.alisoft.xplatform.asf.cache.IMemcachedCache;
import com.alisoft.xplatform.asf.cache.memcached.CacheUtil;
import com.alisoft.xplatform.asf.cache.memcached.MemcachedCacheManager;

public class MemcachedCache {
	private ICacheManager<IMemcachedCache> manager;
	private IMemcachedCache cache;
	
	public MemcachedCache(){
		manager = CacheUtil.getCacheManager(IMemcachedCache.class,
				MemcachedCacheManager.class.getName());
		manager.setConfigFile("memcached.xml");
		manager.setResponseStatInterval(5*1000);
		manager.start();
		cache = manager.getCache("mclient_0");
	}
	
	/**
	 * 获取缓存接口
	 * @return
	 */
	public IMemcachedCache getCache(){
		return cache;
	}
	
	/**
	 * 数据放入缓存
	 * @param key
	 * @param object
	 */
	public void put(String key,Object object){
		
		cache.put(key, object);
	}
	
	/**
	 * 从缓存中读取数据
	 * @param key
	 * @return
	 */
	public Object get(String key){
		return cache.get(key);
	}
}
