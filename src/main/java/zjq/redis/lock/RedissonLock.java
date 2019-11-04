package zjq.redis.lock;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import zjq.redis.resource.RedissonResource;

/**
 * 
 * @author zhangjingqi
 * @date 2019年10月24日下午4:22:32
 *
 **/
@Component
public class RedissonLock {

	final static Logger logger = LoggerFactory.getLogger(RedissonLock.class);

	@Autowired
	private RedissonResource resource;

	private RLock getLock(String lockName) {
		return resource.getClient().getLock(lockName);
	}

	public void lock(String lockName) {
		this.lock(lockName, 0L, null);
	}

	public void lock(String lockName, long leaseTime, TimeUnit unit) {
		RLock lock = this.getLock(lockName);
		if (leaseTime <= 0L || null == unit) {
			lock.lock();
		} else {
			lock.lock(leaseTime, unit);
		}
	}

	public boolean tryLock(String lockName, long waitTime, long leaseTime, TimeUnit unit) {
		RLock lock = this.getLock(lockName);
		try {
			return lock.tryLock(waitTime, leaseTime, unit);
		} catch (InterruptedException e) {
			logger.error("tryLock error : " + e);
		}
		return false;
	}

	public void unLock(RLock lock) {
		if (null != lock)
			lock.unlock();
	}

}
