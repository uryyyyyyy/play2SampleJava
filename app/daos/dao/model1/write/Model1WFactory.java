package daos.dao.model1.write;

import play.Logger;
import util.ConfigUtil;
import daos.dao.model1.write.impl.Model1WDaoDynamo;
import daos.dao.model1.write.impl.Model1WDaoMysql;
import exceptions.SampleMailException;


public class Model1WFactory {

	private static Model1WDao s = init();

	public static Model1WDao get(){
		return s;
	}

	private static Model1WDao init(){
		String s = ConfigUtil.getConfigString("sample.model1.write");
		if(s.equals("mysql")){
			Logger.debug("Model1DaoMysql");
			return new Model1WDaoMysql();
		}else if(s.equals("dynamo")){
			Logger.debug("Model1WDaoDynamo");
			return new Model1WDaoDynamo();
		}else{
			Logger.error("not found: %s", s);
			throw new SampleMailException("not found");
		}
	}

}
