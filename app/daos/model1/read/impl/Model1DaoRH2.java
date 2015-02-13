package daos.model1.read.impl;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import daos.model1.read.Model1RDao;
import daos.model1.utils.entity.Model1Entity;
import exceptions.SamplePersistException;
import util.converter.DateConverter;
import vos.Model1;

import java.util.List;
import java.util.stream.Collectors;

public class Model1DaoRH2 implements Model1RDao {

	@Override
	public List<Model1> all() throws SamplePersistException{
		EbeanServer s = Ebean.getServer("h2");
		return s.find(Model1Entity.class).findList().stream()
				.map(e -> toVo(e))
				.collect(Collectors.toList());
	}

	@Override
	public Model1 findById(String id) throws SamplePersistException {
		EbeanServer s = Ebean.getServer("h2");
		Model1Entity e = s.find(Model1Entity.class, id);
		if(e != null){
			return toVo(e);
		}else{
			throw new SamplePersistException("cannot get id: " + id);
		}
	}

	private Model1 toVo(Model1Entity e) {
		return new Model1(
				e.id,
				e.value,
				e.flag,
				DateConverter.toZonedDateTime(e.dueDate)
		);
	}

}
