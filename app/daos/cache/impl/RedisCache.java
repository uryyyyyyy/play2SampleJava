package daos.cache.impl;

import java.util.Optional;

import daos.cache.Cache;

public class RedisCache implements Cache {

	@Override
	public <T> Optional<T> get(String key) {
		return Optional.empty();
	}

}
