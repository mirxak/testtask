package ru.task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.task.entities.Customer;
import ru.task.repository.CustomerRepository;
import ru.task.service.CustomerService;

import java.util.List;

/**
 * Created by mirak on 11.03.17.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer getById(long id) {
        return customerRepository.findOne(id);
    }

    public List<Customer> getAll() {
        return (List<Customer>) customerRepository.findAll();
    }

    public List<Customer> getByIds(List<Long> ids) {
        return (List<Customer>)customerRepository.findAll(ids);
    }

    public Customer getByNameFio(String nameFio) {
        return customerRepository.findByNameFio(nameFio);
    }

    public Customer getByLogin(String login) {
        return customerRepository.findByLogin(login);
    }
}
