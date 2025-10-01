package service;

import java.util.List;

public interface GenericService<T> {
    T getByID(Long id) ;
    List<T> getAll();
    void deleteById(Long id);
}
