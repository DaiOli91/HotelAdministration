package Interface;

public interface IRepository<T, S> {

    public boolean add(T t);

    public boolean delete(S s);

    public T search(S s);

    public T edit(T t);
}
