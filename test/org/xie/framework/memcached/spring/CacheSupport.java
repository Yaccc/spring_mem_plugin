package org.xie.framework.memcached.spring;
/**
 * 2015-1-14
 * @author xiezhaodong
 *缓存接口，用户自行实现
 */
public interface CacheSupport {
	long CACHE_TIME=2*60*60;//默认缓存时间为2小时
	
	/**
	 * 加入缓存
	 * @param key 键
	 * @param value 值
	 * @return 是否成功
	 */
	 boolean addCache(String key,Object value);
	 
	 /**
	  * 加入缓存，并设置缓存时间
	  * @param key 键
	  * @param value 值
	  * @param time 缓存时间
	  * @return 是否成功
	  */
	 boolean addCache(String key,Object value,long cacheTime);
	 
	 
	 /**
	  * 删除缓存
	  * @param key 键
	  * @return 是否成功
	  */
	 boolean deleteCache(String key);
	 
	 /**
	  * 得到缓存
	  * @param key 键
	  * @return 返回值
	  */
	 Object getCache(String key);
	 
	 /**
	  * 替换缓存中对应的值
	  * @param key 键
	  * @param value 值
	  * @return 是否替换成功
	  */
	 boolean replaceCache(String key,Object value);
	
}
