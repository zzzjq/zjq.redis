package zjq.redis.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * redis缓存服务
 * 
 * @author zhangjingqi
 * @date 2019年10月24日下午3:12:46
 *
 **/
@Component
public class JedisResource {
	
	private static final Logger logger = LoggerFactory.getLogger(JedisResource.class);

	@Autowired
	private JedisPool jedisPool;

	public Jedis getResource() {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
		} catch (Exception e) {
			logger.error("Jedis getResource error :" + e);
		}
		return jedis;
	}
	
	public void returnResource(Jedis jedis) {
		if(null != jedis)
			jedis.close();
	}

}
