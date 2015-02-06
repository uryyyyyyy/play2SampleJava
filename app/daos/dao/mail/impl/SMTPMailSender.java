package daos.dao.mail.impl;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import play.Logger;
import daos.dao.mail.MailSender;
import exceptions.SampleMailException;

public class SMTPMailSender implements MailSender {

	public static final String SMTP_HOST = "dummyHost";
	public static final String MAIL_ADDRESS = "dummy@gmail.com";

	@Override
	public void send() {
		try{
			Email email = new SimpleEmail();
			email.setHostName(SMTP_HOST);
			email.setFrom(MAIL_ADDRESS);
			email.setSubject("TestMail");
			email.setMsg("This is a test mail ... :-)");
			email.addTo(MAIL_ADDRESS);
			email.send();
		}catch(EmailException e){
			Logger.warn("send failed");
			throw new SampleMailException(e);
		}
	}

}
