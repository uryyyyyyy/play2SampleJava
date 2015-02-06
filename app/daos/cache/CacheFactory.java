package daos.cache;

import play.Logger;
import util.ConfigUtil;
import daos.cache.impl.PlayCache;
import daos.cache.impl.RedisCache;
import exception.SampleMailException;


public class CacheFactory {

	private static Cache s = init();

	public static Cache get(){
		return s;
	}

	public static Cache init(){
		String s = ConfigUtil.getConfigString("sample.cache");
		if(s.equals("memory")){
			Logger.debug("memory");
			return new PlayCache();
		}else if(s.equals("redis")){
			Logger.debug("redis");
			return new RedisCache();
		}else{
			Logger.error("not found: %s", s);
			throw new SampleMailException("not found");
		}
	}

}
