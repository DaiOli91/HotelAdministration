package Interface;

public interface IRepository<T> {

    public void add(T t);
    public void delete(String string);
    public T search(String string);
    public T edit(T t);
}
