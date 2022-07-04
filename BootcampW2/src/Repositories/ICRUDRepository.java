package Repositories;
import java.util.List;

public interface ICRUDRepository<T> {

    List<T> findAll();
    T findById(int id);
    void saveToDatabase(T object);
    void deleteFromDatabase(T object);
    void deleteFromDatabase(int id);
    void updateOnDatabase(T object, int id);

}
