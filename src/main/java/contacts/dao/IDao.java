package contacts.dao;

import java.util.List;

/**
 * Created by Pavel on 27.06.2017.
 */
public interface IDao<T> {

    List<T> getAll();

    void add(T entity);

    T getById(int id);

    T getByTitle(String title);

    List<T> getByTitle(String title, String name);

    void delete(T entity);

    void edit(T entity);

}
