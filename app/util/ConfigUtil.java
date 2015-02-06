package util;

import static com.google.common.base.Preconditions.checkNotNull;
import play.Play;

public class ConfigUtil{
	
	public static String getConfigString(String param){
		String s = Play.application().configuration().getString(param);
		checkNotNull(s, "sample.mail");
		return s;
	}

}
