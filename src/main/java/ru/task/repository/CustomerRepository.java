package ru.task.repository;

import org.springframework.data.repository.Repository;
import ru.task.entities.Customer;
import ru.task.repository.base.ReadOnlyRepository;

import java.util.List;

/**
 * Created by mirak on 11.03.17.
 */
public interface CustomerRepository extends ReadOnlyRepository<Customer, Long> {
    Customer findByNameFio(String nameFio);
    Customer findByLogin(String login);
}
