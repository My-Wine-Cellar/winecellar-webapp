package com.cellar.wine.bootstrap;

import com.cellar.wine.model.Producer;
import com.cellar.wine.model.Wine;
import com.cellar.wine.repositories.ProducerRepository;
import com.cellar.wine.repositories.WineRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private WineRepository wineRepository;
    private ProducerRepository producerRepository;

    public DevBootstrap(WineRepository wineRepository, ProducerRepository producerRepository) {
        this.wineRepository = wineRepository;
        this.producerRepository = producerRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {

        Wine pinot = new Wine("BombDotCom", "2013", "Bourgogne", "Pinot", "France", 5);
        wineRepository.save(pinot);

        Producer seasmoke = new Producer("Sea Smoke", "USA", "Russian River Valley");
        producerRepository.save(seasmoke);

    }

}
