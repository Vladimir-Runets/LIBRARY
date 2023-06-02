package by.fpmibsu.LIBRARY.dao;

import java.util.Optional;

public interface GenericDAO<P, K> {
    Optional<K> findById(P id);
    boolean delete(P id);
    void update(K object);
    K save(K entity);
}