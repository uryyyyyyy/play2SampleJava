import daos.DaoFactory;
import exceptions.SampleException;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.libs.F.Promise;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;

import static play.mvc.Results.internalServerError;
import static play.mvc.Results.notFound;

public class Global extends GlobalSettings{
	public Promise<Result> onError(RequestHeader request, Throwable t) {
		if(t instanceof SampleException){
			return Promise.pure(internalServerError("normal error"));
		}else{
			return Promise.pure(internalServerError("unexpected error"));
		}
	}

	public Promise<Result> onHandlerNotFound(RequestHeader request) {
		return Promise.pure(notFound("404"));
	}

	@Override
	public void onStart(Application app) {
		Logger.info("Application has started");
		Logger.info("dao injection");
		DaoFactory.initialize();
	}

	@Override
	public void onStop(Application app) {
		Logger.info("Application shutdown...");
	}

}
