package zjq.redis.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import zjq.redis.serialization.HessianSerializer;
import zjq.redis.util.JedisResource;

/**
 * 
 * @author zhangjingqi
 * @date 2019年10月24日下午3:22:03
 *
 **/
@Component
public class JedisCache {

	@Autowired
	JedisResource jedisResource;
	
	@Autowired
	HessianSerializer hessianSerializer;
}
