package contacts.dao;

import java.util.List;


public interface IDao<T> {

    List<T> getAll();

    T create(T entity);

    T getById(int id);

    T getByTitle(String title);

    List<T> getByTitle(String title, String name);

    void delete(T entity);

    void edit(T entity);

}
