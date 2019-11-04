package zjq.redis.resource;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * 
 * @author zhangjingqi
 * @date 2019年11月4日下午4:26:17
 *
 **/
@Component
public class RedissonResource implements InitializingBean, DisposableBean {

	private RedissonClient client;
	
	private String address;
	
	private String password;
	
	private Integer connectionPoolSize;
	
	private Integer connectionMinimumIdleSize;
	
	private Integer timeout;

	private void buildSingle() {
		Config config = new Config();
		config.useSingleServer().setAddress(this.getAddress())
				.setPassword(this.getPassword())
				.setConnectionPoolSize(this.getConnectionPoolSize())
				.setConnectionMinimumIdleSize(this.getConnectionMinimumIdleSize())
				.setTimeout(this.getTimeout());
		this.setClient(Redisson.create(config));
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		buildSingle();
	}

	@Override
	public void destroy() throws Exception {
		this.client.shutdown();
	}

	public RedissonClient getClient() {
		return client;
	}

	public void setClient(RedissonClient client) {
		this.client = client;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getConnectionPoolSize() {
		return connectionPoolSize;
	}

	public void setConnectionPoolSize(Integer connectionPoolSize) {
		this.connectionPoolSize = connectionPoolSize;
	}

	public Integer getConnectionMinimumIdleSize() {
		return connectionMinimumIdleSize;
	}

	public void setConnectionMinimumIdleSize(Integer connectionMinimumIdleSize) {
		this.connectionMinimumIdleSize = connectionMinimumIdleSize;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

}
