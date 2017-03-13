package ru.task.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.task.entities.PartnerMapping;

import java.util.List;

/**
 * Created by mirak on 11.03.17.
 */
public interface PartnerMappingRepository extends CrudRepository<PartnerMapping, Long> {
    @Transactional
    @Modifying
    void delete(long id);

    @Transactional
    PartnerMapping save(PartnerMapping dish);

    PartnerMapping findOne(Long id);

    List<PartnerMapping> findAll();

    List<PartnerMapping> findAllByCustomerId(Long customerId);
}
