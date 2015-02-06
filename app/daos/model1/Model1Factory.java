package daos.model1;

import play.Logger;
import util.ConfigUtil;
import daos.model1.impl.mysql.Model1DaoMysql;
import daos.model1.impl.redis.Model1DaoRedis;
import exception.SampleMailException;


public class Model1Factory {

	private static Model1Dao s = init();

	public static Model1Dao get(){
		return s;
	}

	private static Model1Dao init(){
		String s = ConfigUtil.getConfigString("sample.model1");
		if(s.equals("mysql")){
			Logger.debug("Model1DaoMysql");
			return new Model1DaoMysql();
		}else if(s.equals("redis")){
			Logger.debug("Model1DaoRedis");
			return new Model1DaoRedis();
		}else{
			Logger.error("not found: %s", s);
			throw new SampleMailException("not found");
		}
	}

}
