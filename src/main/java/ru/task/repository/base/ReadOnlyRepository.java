package ru.task.repository.base;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import ru.task.entities.Customer;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mirak on 12.03.17.
 * Repository только для чтения. Содержит в себе методы, описывающее только операции чтения
 */
@NoRepositoryBean
public interface ReadOnlyRepository<T, ID extends Serializable> extends Repository<T, ID> {
    T findOne(ID id);
    Iterable<T> findAll();
    boolean exists(ID var1);
    Iterable<T> findAll(Iterable<ID> var1);
}
