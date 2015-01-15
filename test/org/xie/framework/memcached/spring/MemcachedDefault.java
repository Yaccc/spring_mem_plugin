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
