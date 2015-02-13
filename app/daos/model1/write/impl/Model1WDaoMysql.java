package daos.model1.write.impl;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import daos.model1.utils.entity.Model1Entity;
import daos.model1.write.Model1WDao;
import exceptions.SamplePersistException;
import play.Logger;
import util.converter.DateConverter;
import vos.Model1;

import javax.persistence.PersistenceException;

public class Model1WDaoMysql implements Model1WDao {

	@Override
	public void create(Model1 vo) throws SamplePersistException {
		EbeanServer s = Ebean.getServer("mysql");
		s.beginTransaction();
		try{
			Model1Entity e = toEntity(vo);
			Logger.debug("create id: "+e.id);
			Logger.debug("create id: %s start", e.id);
			s.save(e);
			s.commitTransaction();
		}catch(PersistenceException e){
			s.rollbackTransaction();
			Logger.error("cannot saved. id: "+vo.id);
			throw new SamplePersistException(e);
		}finally{
			s.endTransaction();
		}
	}

	@Override
	public void delete(String id) throws SamplePersistException {
		EbeanServer s = Ebean.getServer("mysql");
		Model1Entity e = s.find(Model1Entity.class, id);
		s.delete(e);
	}

	@Override
	public void update(Model1 vo) throws SamplePersistException {
		EbeanServer s = Ebean.getServer("mysql");
		Model1Entity e = toEntity(vo);
		s.update(e);
	}

	private Model1Entity toEntity(Model1 vo) {
		return new Model1Entity(
				vo.id,
				vo.value,
				vo.flag,
				DateConverter.toStr(vo.dueDate));
	}

}
