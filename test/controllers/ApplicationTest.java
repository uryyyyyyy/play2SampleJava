package controllers;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.running;
import static play.test.Helpers.testServer;

import org.junit.Test;

import play.libs.ws.WS;
import play.libs.ws.WSResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import dtos.Model1Dto;

public class ApplicationTest {

	@Test
	public void testInServer() {
		running(testServer(3333), new Runnable() {
			public void run() {
				WSResponse res = WS.url("http://localhost:3333").get().get(1000);
				assertThat(res.getStatus()).isEqualTo(OK);
			}
		});
	}

	@Test
	public void testInServer2() {
		running(testServer(3333), new Runnable() {
			public void run() {
				ObjectMapper mapper = new ObjectMapper();
				Model1Dto account = new Model1Dto("id", 100, true, "dd");
				JsonNode node = mapper.convertValue(account, JsonNode.class);
				WSResponse res = WS.url("http://localhost:3333").post(node).get(1000);
				assertThat(res.getBody()).isEqualTo("Hello nasme");
			}
		});
	}
}