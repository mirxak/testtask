package ru.task.service;

import ru.task.entities.PartnerMapping;

import java.util.List;

/**
 * Created by mirak on 11.03.17.
 */
public interface PartnerMappingService {
    PartnerMapping getById(long id);
    List<PartnerMapping> getAll();
    PartnerMapping saveOrUpdate(PartnerMapping partnerMapping);
    void delete(long id);
    List<PartnerMapping> findAllByCustomerId(Long customerId);
}
