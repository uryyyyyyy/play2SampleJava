package daos.mail;

import play.Logger;
import util.ConfigUtil;
import daos.mail.impl.GMailSender;
import daos.mail.impl.SMTPMailSender;
import exceptions.SampleMailException;


public class MailSenderFactory {

	private static MailSender s = init();

	public static MailSender get(){
		return s;
	}

	private static MailSender init(){
		String s = ConfigUtil.getConfigString("sample.mail");
		if(s.equals("gmail")){
			Logger.debug("GMailSender");
			return new GMailSender();
		}else if(s.equals("smtp")){
			Logger.debug("SMTPMailSender");
			return new SMTPMailSender();
		}else{
			Logger.error("not found: %s", s);
			throw new SampleMailException("not found");
		}
	}

}
