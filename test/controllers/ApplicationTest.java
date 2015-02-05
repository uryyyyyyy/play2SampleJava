package controllers;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.BAD_REQUEST;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.callAction;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;
import static play.test.Helpers.status;
import static play.test.Helpers.testServer;
import static play.test.Helpers.fakeRequest;

import org.junit.Test;

import play.libs.ws.WS;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import dto.AccountDto;

public class ApplicationTest {

	@Test
	public void testShowNameResultOk() {
		running(fakeApplication(), new Runnable() {
			@Override
			public void run() {
				ObjectMapper mapper = new ObjectMapper();
				AccountDto account = new AccountDto("id", "nasme");
				JsonNode node = mapper.convertValue(account, JsonNode.class);
				Result result = callAction(
						controllers.routes.ref.Application.sayHello(),
						fakeRequest().withJsonBody(node));
				assertThat(status(result)).isEqualTo(OK);
			}
		});
	}
	@Test
	public void testShowNameResultBadResuestNoForm() {
		running(fakeApplication(), new Runnable() {
			@Override
			public void run() {
				Result result = callAction(
						controllers.routes.ref.Application.sayHello()
						);
				assertThat(status(result)).isEqualTo(BAD_REQUEST);
			}
		});
	}
	@Test
	public void testInServer() {
		running(testServer(3333), new Runnable() {
			public void run() {
				assertThat(
						WS.url("http://localhost:3333").get().get(1000).getStatus()
						).isEqualTo(OK);
			}
		});
	}
}