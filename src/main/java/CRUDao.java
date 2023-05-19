import org.hibernate.Session;

import java.util.List;
import java.util.function.Function;

public interface CRUDao<T,R> extends AutoCloseable {
     <T> T tx(final Function<Session, T> command);

    T add(T t);
    List<T> findById(R id);
    T update(T t);
    T delete(T t);
}
