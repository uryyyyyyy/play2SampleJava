package util.converter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import play.Logger;
import exceptions.SampleException;

public class DateConverter{

	private static final DateTimeFormatter withZone =
			DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss Z");

	public static String toStr(ZonedDateTime zDate){
		return withZone.format(zDate);
	}

	public static ZonedDateTime toZonedDateTime(String dateStr){
		try{
			return ZonedDateTime.parse(dateStr, withZone);
		}catch(DateTimeParseException e){
			Logger.error("ZonedDateTime parse error: "+dateStr);
			throw new SampleException(e);
		}
	}

}
