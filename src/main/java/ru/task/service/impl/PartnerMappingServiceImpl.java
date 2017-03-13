package ru.task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.task.entities.Customer;
import ru.task.entities.PartnerMapping;
import ru.task.repository.PartnerMappingRepository;
import ru.task.service.PartnerMappingService;

import java.util.List;

/**
 * Created by mirak on 11.03.17.
 */
@Service
public class PartnerMappingServiceImpl implements PartnerMappingService {

    @Autowired
    PartnerMappingRepository partnerMappingRepository;

    public PartnerMapping getById(long id) {
        return partnerMappingRepository.findOne(id);
    }

    public List<PartnerMapping> getAll() {
        return partnerMappingRepository.findAll();
    }

    @Override
    public PartnerMapping saveOrUpdate(PartnerMapping partnerMapping) {
        return partnerMappingRepository.save(partnerMapping);
    }

    public void delete(long id) {
        partnerMappingRepository.delete(id);
    }

    @Override
    public List<PartnerMapping> findAllByCustomerId(Long customerId) {
        return partnerMappingRepository.findAllByCustomerId(customerId);
    }
}
