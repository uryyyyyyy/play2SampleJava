package daos.model1.utils.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.ToString;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import com.avaje.ebean.Ebean;

@SuppressWarnings("serial")
@Entity
@ToString
@AllArgsConstructor
@Table(name="model1s")
public class Model1Entity extends Model {

	@Override
	public void save() {
		Ebean.getServer("model1").save(this);
	}

	@Id
	public String id;

	@Constraints.Required
	public long value;

	@Constraints.Required
	public boolean flag;

	@Constraints.Required
	public String dueDate;

	public static Finder<String,Model1Entity> find = 
			new Finder<String,Model1Entity>("model1", String.class, Model1Entity.class); 

}