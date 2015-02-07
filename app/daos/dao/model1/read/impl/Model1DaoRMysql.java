package daos.dao.model1.read.impl;

import java.util.List;
import java.util.stream.Collectors;

import util.converter.DateConverter;
import vos.Model1;
import daos.dao.model1.read.Model1RDao;
import daos.utils.rdbentity.Model1Entity;
import exceptions.SamplePersistException;

public class Model1DaoRMysql implements Model1RDao {

	@Override
	public List<Model1> all() throws SamplePersistException{
		return Model1Entity.find.all()
				.stream().map(e -> toVo(e)).collect(Collectors.toList());
	}

	@Override
	public Model1 findById(String id) throws SamplePersistException {
		Model1Entity e = Model1Entity.find.byId(id);
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
