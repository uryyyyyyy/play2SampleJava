package domain.file;

import play.Logger;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import daos.file.FilerFactory;

public class FileApiSample extends Controller {

	public static Result upload() {
		MultipartFormData body = request().body().asMultipartFormData();
		FilePart f = body.getFile("file");
		if (f != null) {
			Logger.info(f.getFilename());
			Logger.info(f.getContentType());
			FilerFactory.get().save(f.getFilename(), f.getFile());
			return ok("File uploaded");
		} else {
			return badRequest("File cannot find");
		}
	}


}
