package vos;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Model1 {

	public final String id;
	public final long value;
	public final boolean flag;
	public final ZonedDateTime dueDate;

}
