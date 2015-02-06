package daos.dao.model1;

import play.Logger;
import util.ConfigUtil;
import daos.dao.model1.impl.Model1DaoMysql;
import daos.dao.model1.impl.Model1DaoRedis;
import exceptions.SampleMailException;


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