package Interface;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IRepository<T, S> {

    public boolean add(T t);

    public boolean delete(S s);

    public T search(S s);

    public void edit(T t);

    public void writeGson() throws FileNotFoundException, IOException, JsonIOException, JsonSyntaxException;

    public void readGson() throws FileNotFoundException, IOException, JsonIOException, JsonSyntaxException;
}
