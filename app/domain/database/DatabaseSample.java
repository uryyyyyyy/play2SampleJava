package domain.database;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import play.mvc.Controller;
import play.mvc.Result;
import vos.Model1;
import daos.model1.read.Model1RFactory;
import daos.model1.write.Model1WFactory;

public class DatabaseSample extends Controller {

	public static Result write(String id) {
		Model1 vo = new Model1(id,
				100,
				true,
				ZonedDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault())
				);
		Model1WFactory.get().create(vo);
		return ok("create");
	}

	public static Result readAll() {
		List<Model1> list = Model1RFactory.get().all();
		return ok(list.toString());
	}

	public static Result read(String id) {
		Model1 m = Model1RFactory.get().findById(id);
		return ok(m.toString());
	}

}
