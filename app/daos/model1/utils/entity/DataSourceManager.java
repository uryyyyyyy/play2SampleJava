package daos.model1.utils.entity;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;

public class DataSourceManager {
	public static EbeanServer getDataSource(){
		return Ebean.getServer("model1");
	}
}
