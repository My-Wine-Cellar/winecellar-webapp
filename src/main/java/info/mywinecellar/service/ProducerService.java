/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.converter.ProducerConverter;
import info.mywinecellar.dto.ProducerDto;
import info.mywinecellar.model.Producer;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

/**
 * Producer service
 */
@Component
public class ProducerService extends AbstractService<Producer> {

    @Inject
    ProducerConverter producerConverter;

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
     * Edit a Producer
     *
     * @param producerDto ProducerDto producerDto
     * @param producerId  Long producerId
     */
    @Transactional
    public void editProducer(ProducerDto producerDto, Long producerId) {
        Producer saveProducer = this.findById(producerId);
        saveProducer = producerConverter.toEntity(saveProducer, producerDto);
        this.save(saveProducer);
    }

}
