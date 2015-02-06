package daos.model1.impl.mysql;

import java.util.List;

import daos.model1.Model1Dao;

public class Model1DaoMysql implements Model1Dao {

	@Override
	public List<Model1Entity> all() {
		return Model1Entity.find.all();
	}

	public void create(Model1Entity task) {
		task.save();
	}

	public void delete(Long id) {
		Model1Entity.find.ref(id).delete();
	}

}
