package domain.mail;

import daos.DaoFactory;
import exceptions.SampleMailException;
import play.mvc.Controller;
import play.mvc.Result;

public class MailSample extends Controller {

	public static Result index() {
		try {
			DaoFactory.mailSender.send();
			return ok("mail send");
		} catch (SampleMailException e) {
			return ok("mail fail");
		}
	}

}
