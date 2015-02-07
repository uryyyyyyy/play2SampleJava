package util;

import play.Play;
import exceptions.SampleException;

public class ConfigUtil{
	
	public static String getConfigString(String param){
		String s = Play.application().configuration().getString(param);
		if(s != null){
			return s;
		}else{
			throw new SampleException("cannot load: "+param);
		}
	}

}
