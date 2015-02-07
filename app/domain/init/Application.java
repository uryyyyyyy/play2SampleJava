package domain.init;


import java.io.IOException;

import play.Logger;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import util.converter.JsonConverter;
import vos.Account;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

import dtos.Model1Dto;

public class Application extends Controller {

	@BodyParser.Of(BodyParser.Json.class)
	public static Result sayHello() throws JsonParseException, JsonMappingException, IOException {
		JsonNode json = request().body().asJson();
		Logger.info("ssss");
		Model1Dto account = JsonConverter.toPojo(json);
		return ok("Hello " + account.id);
	}
	
	public static Result index() throws JsonParseException, JsonMappingException, IOException {
		Account account = new Account("id", "name");
		JsonNode node = JsonConverter.toJsonNode(account);
		
		return ok(node);
	}


}
