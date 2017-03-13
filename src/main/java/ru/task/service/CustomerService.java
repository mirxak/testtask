package ru.task.service;

import ru.task.entities.Customer;

import java.util.List;

/**
 * Created by mirak on 11.03.17.
 */
public interface CustomerService {
    Customer getById(long id);
    List<Customer> getAll();
    List<Customer> getByIds(List<Long> ids);
    Customer getByNameFio(String nameFio);
    Customer getByLogin(String login);
}