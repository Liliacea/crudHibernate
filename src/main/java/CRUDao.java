import java.util.List;

public interface CRUDao<T,R> extends AutoCloseable {

    T add(T t);
    List<T> findById(R id);
    T update(T t);
    T delete(T t);
}
