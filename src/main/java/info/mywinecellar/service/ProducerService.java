/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.converter.ProducerConverter;
import info.mywinecellar.dto.ProducerDto;
import info.mywinecellar.model.Producer;

import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Component;

/**
 * Producer service
 */
@Component
public class ProducerService extends AbstractService<Producer> {

    protected ProducerService() {
        super(Producer.class);
    }

    /**
     * Find by name
     *
     * @param name The name
     * @return The producer
     */
    public Producer findByName(String name) {
        try {
            Query q = em.createQuery("SELECT p FROM Producer p WHERE p.name = :name");
            q.setParameter("name", name);
            return (Producer) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Find a producer by it's lowercase name
     *
     * @param lcName String lowercase name
     * @return Producer entity
     */
    public Producer findByLowerCaseName(String lcName) {
        try {
            Query query = em.createNativeQuery("SELECT * FROM producer p WHERE lower(p.name) = :lc_name",
                    Producer.class);
            query.setParameter("lc_name", lcName);
            return (Producer) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Edit a Producer
     *  @param producerDto ProducerDto producerDto
     * @param producerId  Long producerId
     * @return Producer
     */
    @Transactional
    public Producer editProducer(ProducerDto producerDto, Long producerId) {
        Producer entity = ProducerConverter.toEntity(this.findById(producerId), producerDto);
        this.save(entity);
        return entity;
    }

}
