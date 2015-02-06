package daos.cache;

import java.util.Optional;

public interface Cache {

	public <T> Optional<T> get(String key);

}
