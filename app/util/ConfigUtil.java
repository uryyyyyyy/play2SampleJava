package util;

import static com.google.common.base.Preconditions.checkNotNull;
import play.GlobalSettings;
import play.Play;

public class ConfigUtil extends GlobalSettings{
	
	public static String getConfigString(String param){
		String s = Play.application().configuration().getString(param);
		checkNotNull(s, "sample.mail");
		return s;
	}

}
