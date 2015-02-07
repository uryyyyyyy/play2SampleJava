package dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Model1Dto {

	public String id;
	public long value;
	public boolean flag;
	public String dueDate;

}
