package domain.database;

import java.util.List;

import play.mvc.Controller;
import play.mvc.Result;
import daos.dao.model1.Model1Factory;
import daos.entity.Model1Entity;

public class DatabaseSample extends Controller {

	public static Result index() {
		List<Model1Entity> list = Model1Factory.get().all();
		return ok(list.toString());
	}

}
