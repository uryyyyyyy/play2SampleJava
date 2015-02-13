package daos;

import daos.cache.Cache;
import daos.cache.impl.PlayCache;
import daos.cache.impl.RedisCache;
import daos.file.Filer;
import daos.file.impl.LocalFiler;
import daos.file.impl.S3Filer;
import daos.mail.MailSender;
import daos.mail.impl.GMailSender;
import daos.mail.impl.SMTPMailSender;
import daos.model1.read.Model1RDao;
import daos.model1.read.impl.Model1DaoRDynamo;
import daos.model1.read.impl.Model1DaoRH2;
import daos.model1.read.impl.Model1DaoRMysql;
import daos.model1.write.Model1WDao;
import daos.model1.write.impl.Model1WDaoDynamo;
import daos.model1.write.impl.Model1WDaoMysql;
import exceptions.SampleFileException;
import exceptions.SampleMailException;
import play.Logger;
import util.ConfigUtil;


public class DaoFactory {

	public static Filer filer = filerInit();
	public static Cache cache = cacheInit();
	public static Model1RDao model1RDao = model1RInit();
	public static Model1WDao model1WDao = model1WInit();
	public static MailSender mailSender = mailSenderInit();

	private static void logging(Class clazz){
		Logger.debug(clazz.getName() + " load");
	}

	public static void initialize(){
		logging(filer.getClass());
		logging(cache.getClass());
		logging(model1RDao.getClass());
		logging(model1WDao.getClass());
		logging(mailSender.getClass());
	}

	private static Cache cacheInit(){
		String s = ConfigUtil.getConfigString("sample.cache");
		switch (s) {
			case "redis":
				return new RedisCache();
			default:
				return new PlayCache();
		}
	}

	private static Filer filerInit(){
		String s = ConfigUtil.getConfigString("sample.file.impl");
		switch (s) {
			case "s3":
				return new S3Filer();
			default:
				return new LocalFiler();
		}
	}

	private static Model1RDao model1RInit(){
		String s = ConfigUtil.getConfigString("sample.model1.read");
		switch (s) {
			case "mysql":
				return new Model1DaoRMysql();
			case "dynamo":
				return new Model1DaoRDynamo();
			default:
				return new Model1DaoRH2();
		}
	}

	private static Model1WDao model1WInit(){
		String s = ConfigUtil.getConfigString("sample.model1.write");
		switch (s) {
			case "mysql":
				return new Model1WDaoMysql();
			case "dynamo":
				return new Model1WDaoDynamo();
			default:
				Logger.error("not found: %s", s);
				throw new SampleMailException("not found:" + s);
		}
	}

	private static MailSender mailSenderInit(){
		String s = ConfigUtil.getConfigString("sample.mail");
		switch (s) {
			case "gmail":
				return new GMailSender();
			case "smtp":
				return new SMTPMailSender();
			default:
				throw new SampleMailException("not found:" + s);
		}
	}

}
