package domain;

import play.Logger;
import play.libs.F;
import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.Result;

public class VerboseAction extends Action.Simple {

	public F.Promise<Result> call(Context ctx) throws Throwable {
		Logger.info("Calling action for " + ctx);
		return delegate.call(ctx);
	}
}
