package zjq.redis.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import zjq.redis.resource.JedisResource;
import zjq.redis.serialization.HessianSerializer;

/**
 * 
 * @author zhangjingqi
 * @date 2019年10月24日下午3:22:03
 *
 **/
@Component
public class JedisCache {
	
	private static final Logger logger = LoggerFactory.getLogger(JedisCache.class);

	@Autowired
	JedisResource jedisResource;
	
	@Autowired
	HessianSerializer hessianSerializer;
	
	public void set(String key, String value) {
		Jedis jedis = jedisResource.getResource();
		if(null == jedis)
			return;
		try {
			jedis.set(key, value);
		} catch (Exception e) {
			logger.error("redis set error :" + e);
		} finally {
			jedisResource.returnResource(jedis);
		}
	}
	
	public void set(Object key, Object value) {
		Jedis jedis = jedisResource.getResource();
		if(null == jedis)
			return;
		try {
			jedis.set(hessianSerializer.serialize(key), hessianSerializer.serialize(value));
		} catch (Exception e) {
			logger.error("redis set error :" + e);
		} finally {
			jedisResource.returnResource(jedis);
		}
	}
}
