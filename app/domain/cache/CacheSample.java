package domain.cache;

import daos.DaoFactory;
import play.mvc.Controller;
import play.mvc.Result;
import vos.Account;

import java.util.Optional;

public class CacheSample extends Controller {

	public static Result index() {
		Account acc = new Account("id1", "name1");
		DaoFactory.cache.set("acc", acc);
		
		Optional<Account> a = DaoFactory.cache.get("acc");
		Account a_ = a.orElse(new Account("id1", "default"));
		return ok(a_.name + " done");
	}

}
