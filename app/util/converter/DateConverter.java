package util.converter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateConverter{

	private static final DateTimeFormatter withZone =
			DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss Z");

	public static String toStr(ZonedDateTime zDate){
		return withZone.format(zDate);
	}

	public static ZonedDateTime toZonedDateTime(String dateStr){
		return ZonedDateTime.parse(dateStr, withZone);
	}

}
