package util;

import static play.mvc.Results.internalServerError;
import static play.mvc.Results.notFound;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.libs.F.Promise;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;
import exception.SampleException;

public class Global extends GlobalSettings{
	public Promise<Result> onError(RequestHeader request, Throwable t) {
		if(t instanceof SampleException){
			return Promise.<Result>pure(internalServerError("normal error"));
		}else{
			return Promise.<Result>pure(internalServerError("unexpected error"));
		}
	}

	public Promise<Result> onHandlerNotFound(RequestHeader request) {
		return Promise.<Result>pure(notFound("404"));
	}

	public void onStart(Application app) {
		Logger.info("Application has started");
	}

	public void onStop(Application app) {
		Logger.info("Application shutdown...");
	}

}
