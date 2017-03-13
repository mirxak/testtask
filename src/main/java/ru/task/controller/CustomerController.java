package ru.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.task.entities.Customer;
import ru.task.service.CustomerService;
import ru.task.utils.UserContextHolder;

import java.util.List;

/**
 * Created by mirak on 11.03.17.
 */
@Controller
@RequestMapping(value = "rest/" + Customer.CATEGORY, produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

    private static final String CURRENT_USER = "@me";

    @Autowired
    CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> getCustomers(){
        return customerService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Customer getCustomer(@PathVariable(value = "id")String id){
        Long customerId;
        if (CURRENT_USER.equals(id)){
            customerId = UserContextHolder.getData().getCustomerId();
        }
        else{
            customerId = Long.parseLong(id);
        }

        return customerService.getById(customerId);
    }

    @RequestMapping(value = "/fio/{nameFio}", method = RequestMethod.GET)
    @ResponseBody
    public Customer getByNameFio(@PathVariable(value = "nameFio")String nameFio){
        return customerService.getByNameFio(nameFio);
    }

    @RequestMapping(value = "/login/{login}", method = RequestMethod.GET)
    @ResponseBody
    public Customer getByLogin(@PathVariable(value = "login")String login){
        return customerService.getByLogin(login);
    }
}
