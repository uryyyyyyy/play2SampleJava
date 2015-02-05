package controllers;

import java.io.IOException;

import play.Logger;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import vos.Account;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import dto.AccountDto;

public class Application extends Controller {

	@BodyParser.Of(BodyParser.Json.class)
	public static Result sayHello() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode json = request().body().asJson();
		Logger.info("ssss");
		Logger.info(json.toString());
		AccountDto account = mapper.readValue(json.toString(), AccountDto.class);
		return ok("Hello " + account.name);
	}
	
	public static Result index() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Account account = new Account("id", "name");
		JsonNode node = mapper.convertValue(account, JsonNode.class);
		
		return ok(node);
	}


}
