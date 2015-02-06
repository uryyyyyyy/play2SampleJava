package daos.model1;

import java.util.List;

import daos.model1.impl.mysql.Model1Entity;


public interface Model1Dao {
	
	public List<Model1Entity> all();

}
