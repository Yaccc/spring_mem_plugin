package org.xie.framework.memcached.spring;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;

/**
 * Aop切面类，实际的作用类
 * 
 * @author xiezhaodong
 * 
 */
public class GetCacheAop {

	private static final Log log = LogFactory.getLog(GetCacheAop.class);

	private CacheSupport cacheSupport;

	public void setCacheSupport(CacheSupport cacheSupport) {
		this.cacheSupport = cacheSupport;
	}

	public Object ProxyInvoke(ProceedingJoinPoint pjp) {
		log.info("invoke proxyinvoke method");
		Object result = null;
		// 得到运行的方法
		Method method = getMethod(pjp);
		Annotation[] annotations = method.getDeclaredAnnotations();

		if (annotations.length == 0) {//如果不存在注解，直接执行不缓存
			try {			
				result = pjp.proceed();
				return result;
			} catch (Throwable e) {
				log.warn("your method " + method.getName()+ " have some errors");
			}
		}
		// --------------------------
		String cacheKey = getCacheKey(pjp, method);

		result = get_or_input_cache(pjp, result, cacheKey);
		
		return result;
		
	}
	/**
	 * 得到缓存或者从数据库中查到并且放入缓存
	 * @param pjp
	 * @param result
	 * @param cacheKey
	 * @return
	 */
	private Object get_or_input_cache(ProceedingJoinPoint pjp, Object result,
			String cacheKey) {
		if (cacheKey != null) {
			result = cacheSupport.getCache(cacheKey);// 得到缓存，修改result的值
			if(result==null){//如果该缓存里面没有,得到result并缓存到缓存服务其中
				try {
					result=pjp.proceed();
					cacheSupport.addCache(cacheKey,result);		
					return result;
				} catch (Throwable e) {
					log.warn("invoke default");
				}		
			}
			return result;//缓存存在，直接返回result
		}else{//如果没有该注解直接执行方法
			try {
				result=pjp.proceed();
				
			} catch (Throwable e) {
				log.warn("invoke default");
			}
		}
		return result;
	}

	/**
	 * 取得cache 键值
	 * 
	 * @param pjp
	 * @param method
	 * @return 返回string
	 */
	private String getCacheKey(ProceedingJoinPoint pjp, Method method) {
		if (method.isAnnotationPresent(GetCacheVaule.class)) {
			// 如果有该注解
			String key = method.getAnnotation(GetCacheVaule.class).key();// 得到要缓存的键值

			Object[] values = pjp.getArgs();// 得到顺序的参数值
			ParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
			String[] names = discoverer.getParameterNames(method);
			Map<String, Integer> map = new ConcurrentHashMap<String, Integer>();
			for (int i = 0; i < names.length; i++) {
				map.put(names[i], i);// 将名字和对应的序号放入hashmap
			}
			// 得到真正的key 、value值
			try {
				Integer int_value = map.get(key);// hash中没有对应的值，表示getcachekey和名字不符合
				if (int_value == null) {
					log.warn("your cachekey is not equals" + key
							+ "please check this then change them");					
				} else {
					String cache_key_real = (String) values[int_value];// 要缓存键值的真正cahe值
					return cache_key_real;
				}
			} catch (Exception e) {
				log.warn("your filed " + key + " must be String.class");
			}

		}
		return null;
	}

	/**
	 * 得到运行时方法对象
	 * 
	 * @param pjp
	 * @return
	 */
	private Method getMethod(ProceedingJoinPoint pjp) {
		MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
		Method method = methodSignature.getMethod();
		return method;
	}

}
