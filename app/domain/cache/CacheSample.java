package domain.cache;

import java.util.Optional;

import play.mvc.Controller;
import play.mvc.Result;
import vos.Account;
import daos.cache.CacheFactory;

public class CacheSample extends Controller {

	public static Result index() {
		Optional<Account> a = CacheFactory.get().<Account>get("ss");
		Account a_ = a.orElse(new Account("id1", "name1"));
		return ok(a_.name + " done");
	}

}
