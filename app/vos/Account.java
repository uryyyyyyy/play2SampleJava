package vos;

import lombok.EqualsAndHashCode;
import lombok.ToString;


@ToString
@EqualsAndHashCode
public class Account {

	public final String id;
	public final String name;
	public Account(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

}
