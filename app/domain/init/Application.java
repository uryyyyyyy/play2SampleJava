package domain.init;


import com.fasterxml.jackson.databind.JsonNode;
import domain.VerboseAction;
import dtos.Model1Dto;
import play.Logger;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import util.converter.JsonConverter;
import vos.Account;

public class Application extends Controller {

	@BodyParser.Of(BodyParser.Json.class)
	public static Result sayHello() {
		JsonNode json = request().body().asJson();
		Logger.info("ssss");
		Model1Dto account = JsonConverter.toPojo(json);
		return ok("Hello " + account.id);
	}

	@With(VerboseAction.class)
	public static Result index() {
		Account account = new Account("id", "name");
		JsonNode node = JsonConverter.toJsonNode(account);
		ScalaSample ss = new ScalaSample(3, "title");
		Logger.info(ss.title());
		ScalaSample.func();
		return ok(node);
	}


}
