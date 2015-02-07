package util.converter;

import java.io.IOException;

import play.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import exceptions.SampleJsonException;

public class JsonConverter{

	private static ObjectMapper mapper = new ObjectMapper();


	/**
	 * @exception SampleJsonException
	 */
	public static <T> String toJson(T obj){
		try {
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			Logger.error("JsonProcessingException error");
			throw new SampleJsonException();
		}
	}

	/**
	 * @exception SampleJsonException
	 */
	public static <T> T toPojo(String json){
		try {
			return mapper.readValue(json, new TypeReference<T>(){});
		} catch (IOException e) {
			Logger.error("convert error : %s", json);
			throw new SampleJsonException(e);
		}
	}

	public static <T> JsonNode toJsonNode(T obj){
		return mapper.convertValue(obj, JsonNode.class);
	}

	/**
	 * @exception SampleJsonException
	 */
	public static <T> T toPojo(JsonNode json){
		try {
			return  mapper.readValue(json.toString(), new TypeReference<T>(){});
		} catch (IOException e) {
			Logger.error("convert error : %s", json);
			throw new SampleJsonException(e);
		}
	}

}
