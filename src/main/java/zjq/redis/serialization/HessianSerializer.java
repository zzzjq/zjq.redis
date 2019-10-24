package zjq.redis.serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Component;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

/**
 * Hessian序列化
 * 
 * @author zhangjingqi
 * @date 2019年10月24日下午3:37:02
 *
 **/
@Component
public class HessianSerializer {

	public byte[] serialize(Object o) {
		if (null == o)
			throw new NullPointerException("Hessian serialize error, object is null");
		try (ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			HessianOutput hout = new HessianOutput(out);
			hout.writeObject(o);
			return out.toByteArray();
		} catch (Exception e) {
		}
		return null;
	}

	public Object deserialize(byte[] data) {
		if (null == data || data.length < 1)
			throw new NullPointerException("Hessian deserialize error, data is null");
		try (ByteArrayInputStream in = new ByteArrayInputStream(data);) {
			return deserialize(in);
		} catch (Exception e) {
		}
		return null;
	}

	public Object deserialize(InputStream in) {
		HessianInput hin = new HessianInput(in);
		try {
			return hin.readObject();
		} catch (IOException e) {
		}
		return null;
	}

}
