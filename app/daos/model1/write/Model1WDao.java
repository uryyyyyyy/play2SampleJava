package daos.model1.write;

import exceptions.SamplePersistException;
import vos.Model1;


public interface Model1WDao {

	public void create(Model1 vo) throws SamplePersistException;

	public void delete(String id) throws SamplePersistException;

	public void update(Model1 vo) throws SamplePersistException;
}
