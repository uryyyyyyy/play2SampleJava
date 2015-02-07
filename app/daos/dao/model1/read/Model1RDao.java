package daos.dao.model1.read;

import java.util.List;

import vos.Model1;
import exceptions.SamplePersistException;


public interface Model1RDao {
	
	public List<Model1> all() throws SamplePersistException;
	
	public Model1 findById(String id) throws SamplePersistException;

}
