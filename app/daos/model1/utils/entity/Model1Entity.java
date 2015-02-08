package daos.model1.utils.entity;

import lombok.AllArgsConstructor;
import lombok.ToString;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@ToString
@AllArgsConstructor
@Table(name="model1s")
public class Model1Entity extends Model {

	@Id
	public String id;

	@Constraints.Required
	public long value;

	@Constraints.Required
	public boolean flag;

	@Constraints.Required
	public String dueDate;

}