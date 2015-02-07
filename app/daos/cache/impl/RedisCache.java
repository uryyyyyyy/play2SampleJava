package daos.cache.impl;

import java.util.Optional;

import redis.clients.jedis.Jedis;
import util.converter.JsonConverter;
import daos.cache.Cache;

public class RedisCache implements Cache {

	private static Jedis jedis = new Jedis("localhost");

	@Override
	public <T> Optional<T> get(String key) {
		return Optional.ofNullable(jedis.get(key))
				.map(json -> JsonConverter.<T>toPojo(json));
	}

	@Override
	public <T> void set(String key, T value) {
		String json = JsonConverter.<T>toJson(value);
		jedis.set(key, json);
	}

}
