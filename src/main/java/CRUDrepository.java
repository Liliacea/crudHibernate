public interface CRUDrepository<T,R> extends AutoCloseable {

    T add(T t);
    T findById(R id);
    T update(T t);
    T delete(T t);
}
