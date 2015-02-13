package domain.file;

import daos.DaoFactory;
import exceptions.SampleException;
import play.mvc.Controller;
import play.mvc.Result;

import java.io.File;
import java.util.List;

public class FileSample extends Controller {

	public static Result find(String id) {
		try{
			File f = DaoFactory.filer.find(id);
			return ok(f);
		}catch(SampleException e){
			return ok("cannot found: "+id);
		}
	}

	public static Result list() {
		List<String> files = DaoFactory.filer.list();
		return ok(files.toString());
	}

	public static Result save(String id) {
		File file = new File("tmp/image.png");
		DaoFactory.filer.save(id, file);
		return ok("file saved!");
	}

}
