package ru.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.task.entities.Customer;
import ru.task.entities.PartnerMapping;
import ru.task.service.CustomerService;
import ru.task.service.PartnerMappingService;
import ru.task.utils.JsonUtils;
import ru.task.utils.UserContextHolder;

import java.io.IOException;
import java.util.List;

/**
 * Created by mirak on 11.03.17.
 */
@Controller
@RequestMapping(value = "rest/" + PartnerMapping.CATEGORY, produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class PartnerMappingController {

    @Autowired
    PartnerMappingService partnerMappingService;

    @Autowired
    CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<PartnerMapping> getPartnerMappings(){
        return partnerMappingService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PartnerMapping getPartnerMapping(@PathVariable(value = "id")String id){
        return partnerMappingService.getById(Long.parseLong(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public PartnerMapping addPartnerMapping(@RequestBody String json){
        PartnerMapping partnerMapping;
        try {
            partnerMapping = JsonUtils.getFromJson(json, PartnerMapping.class);
        } catch (IOException e) {
            throw new RuntimeException("Parse error");
        }

        if (!UserContextHolder.getData().getCustomerId().equals(partnerMapping.getCustomerId())){
            throw new RuntimeException("You can't add new partnerMappings for other customers");
        }

        partnerMapping.setCustomer(customerService.getById(partnerMapping.getCustomerId()));
        return partnerMappingService.saveOrUpdate(partnerMapping);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseBody
    public PartnerMapping updatePartnerMapping(@PathVariable(value = "id")String id,
                                               @RequestBody String json){
        PartnerMapping partnerMapping = partnerMappingService.getById(Long.parseLong(id));
        if (partnerMapping == null){
            throw new RuntimeException("Entity with id=" + id + "doesn't exist");
        }

        if (!UserContextHolder.getData().getCustomerId().equals(partnerMapping.getCustomerId())){
            throw new RuntimeException("You can't update partnerMappings of other customers");
        }

        Customer customer = partnerMapping.getCustomer();
        try {
            partnerMapping = JsonUtils.update(json, partnerMapping);
        } catch (IOException e) {
            throw new RuntimeException("Error in updating entity from json");
        }
        partnerMapping.setCustomer(customer);

        return partnerMappingService.saveOrUpdate(partnerMapping);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deletePartnerMapping(@PathVariable(value = "id")String id){
        partnerMappingService.delete(Long.parseLong(id));
        return "deleted";
    }
}
