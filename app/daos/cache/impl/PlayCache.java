package daos.cache.impl;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import daos.cache.Cache;

public class PlayCache implements Cache {
	
	private static ConcurrentHashMap<String, Object> cache = new ConcurrentHashMap<>();

	@SuppressWarnings("unchecked")
	@Override
	public <T> Optional<T> get(String key) {
		Object v = cache.get(key);
		try{
			return Optional.of((T)v);
		}catch(ClassCastException  | NullPointerException e){
			return Optional.empty();
		}
	}

	@Override
	public <T> void set(String key, T value) {
		cache.put(key, value);
	}

}
