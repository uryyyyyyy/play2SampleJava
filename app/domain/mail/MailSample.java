package domain.mail;

import play.mvc.Controller;
import play.mvc.Result;
import daos.mail.MailSender;
import daos.mail.impl.SMTPMailSender;
import exception.SampleMailException;

public class MailSample extends Controller {
	
	public static Result index() {
		MailSender sender = new SMTPMailSender();
		try {
			sender.send();
			return ok("mail send");
		} catch (SampleMailException e) {
			return ok("mail fail");
		}
	}

}
