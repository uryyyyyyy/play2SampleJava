package domain.mail;

import play.mvc.Controller;
import play.mvc.Result;
import daos.mail.MailSenderFactory;
import exceptions.SampleMailException;

public class MailSample extends Controller {

	public static Result index() {
		try {
			MailSenderFactory.get().send();
			return ok("mail send");
		} catch (SampleMailException e) {
			return ok("mail fail");
		}
	}

}
