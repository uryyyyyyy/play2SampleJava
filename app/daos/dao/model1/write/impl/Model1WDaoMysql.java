package daos.dao.model1.write.impl;

import javax.persistence.PersistenceException;

import play.Logger;
import util.converter.DateConverter;
import vos.Model1;
import daos.dao.model1.write.Model1WDao;
import daos.entity.Model1Entity;
import exceptions.SamplePersistException;

public class Model1WDaoMysql implements Model1WDao {

	@Override
	public void create(Model1 vo) throws SamplePersistException {
		try{
			Model1Entity e = toEntity(vo);
			Logger.debug("create id: "+e.id);
			Logger.debug("create id: %s start", e.id);
			e.save();
		}catch(PersistenceException e){
			Logger.error("cannot saved");
			throw new SamplePersistException(e);
		}
	}

	@Override
	public void delete(String id) throws SamplePersistException {
		Model1Entity.find.ref(id).delete();
	}

	@Override
	public void update(Model1 vo) throws SamplePersistException {
		Model1Entity e = toEntity(vo);
		e.save();
	}

	private Model1Entity toEntity(Model1 vo) {
		return new Model1Entity(
				vo.id,
				vo.value,
				vo.flag,
				DateConverter.toStr(vo.dueDate));
	}

}
