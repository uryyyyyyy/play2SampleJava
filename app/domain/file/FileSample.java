package domain.file;

import java.io.File;
import java.util.List;

import play.mvc.Controller;
import play.mvc.Result;
import daos.file.FilerFactory;
import exceptions.SampleException;

public class FileSample extends Controller {

	public static Result find(String id) {
		try{
			File f = FilerFactory.get().find(id);
			return ok(f);
		}catch(SampleException e){
			return ok("cannot found: "+id);
		}
	}

	public static Result list() {
		List<String> files = FilerFactory.get().list();
		return ok(files.toString());
	}

	public static Result save(String id) {
		File file = new File("tmp/.gitkeep");
		FilerFactory.get().save(id, file);
		return ok("file saved!");
	}

}
