package com.cellar.wine.bootstrap;

import com.cellar.wine.model.Importer;
import com.cellar.wine.model.Producer;
import com.cellar.wine.model.Wine;
import com.cellar.wine.repositories.ImporterRepository;
import com.cellar.wine.repositories.ProducerRepository;
import com.cellar.wine.repositories.WineRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private WineRepository wineRepository;
    private ProducerRepository producerRepository;
    private ImporterRepository importerRepository;

    public DevBootstrap(WineRepository wineRepository, ProducerRepository producerRepository, ImporterRepository importerRepository) {
        this.importerRepository = importerRepository;
        this.wineRepository = wineRepository;
        this.producerRepository = producerRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {

        Importer importer = new Importer("Pearson's Imports");
        importerRepository.save(importer);

        Wine pinot = new Wine("BombDotCom", "2013", "Bourgogne", "Pinot", "France", 5);
        wineRepository.save(pinot);

        Producer seasmoke = new Producer("Sea Smoke", "USA", "Russian River Valley");
        producerRepository.save(seasmoke);

    }

}
