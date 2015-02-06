package daos.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

@SuppressWarnings("serial")
@Entity 
public class Model1Entity extends Model {

	@Id
	@Constraints.Min(10)
	public Long id;

	@Constraints.Required
	public String name;

	public boolean done;

	@Formats.DateTime(pattern="dd/MM/yyyy")
	public Date dueDate = new Date();

	public static Finder<Long,Model1Entity> find = new Finder<Long,Model1Entity>(
			Long.class, Model1Entity.class
			); 

}