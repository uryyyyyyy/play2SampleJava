package daos.file;

import play.Logger;
import util.ConfigUtil;
import daos.file.impl.LocalFiler;
import daos.file.impl.S3Filer;
import exceptions.SampleFileException;


public class FilerFactory {

	private static Filer s = init();

	public static Filer get(){
		return s;
	}

	private static Filer init(){
		String s = ConfigUtil.getConfigString("sample.file.impl");
		if(s.equals("local")){
			Logger.debug("LocalFiler");
			return new LocalFiler();
		}else if(s.equals("s3")){
			Logger.debug("S3Filer");
			return new S3Filer();
		}else{
			Logger.error("not found: %s", s);
			throw new SampleFileException("not found");
		}
	}

}
