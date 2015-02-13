package domain.database;

import daos.DaoFactory;
import play.mvc.Controller;
import play.mvc.Result;
import vos.Model1;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

public class DatabaseSample extends Controller {

	public static Result write(String id) {
		Model1 vo = new Model1(id,
				100,
				true,
				ZonedDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault())
				);
		DaoFactory.model1WDao.create(vo);
		return ok("create");
	}

	public static Result readAll() {
		List<Model1> list = DaoFactory.model1RDao.all();
		return ok(list.toString());
	}

	public static Result read(String id) {
		Model1 m = DaoFactory.model1RDao.findById(id);
		return ok(m.toString());
	}

}
