package domain.session;


import play.mvc.Controller;
import play.mvc.Result;

public class SessionSample extends Controller {

	public static Result createSession(){
		session("connected", "session_value");
		return ok("Welcome!");
	}

	public static Result clearSession() {
		session().clear();
		return ok("Bye");
	}


}
