package daos.dao.model1.read;

import play.Logger;
import util.ConfigUtil;
import daos.dao.model1.read.impl.Model1DaoRDynamo;
import daos.dao.model1.read.impl.Model1DaoRMysql;
import exceptions.SampleMailException;


public class Model1RFactory {

	private static Model1RDao s = init();

	public static Model1RDao get(){
		return s;
	}

	private static Model1RDao init(){
		String s = ConfigUtil.getConfigString("sample.model1.read");
		if(s.equals("mysql")){
			Logger.debug("Model1DaoMysql");
			return new Model1DaoRMysql();
		}else if(s.equals("dynamo")){
			Logger.debug("Model1DaoRDynamo");
			return new Model1DaoRDynamo();
		}else{
			Logger.error("not found: %s", s);
			throw new SampleMailException("not found");
		}
	}

}
