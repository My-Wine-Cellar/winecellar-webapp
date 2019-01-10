package com.cellar.wine.bootstrap;

import com.cellar.wine.models.Producer;
import com.cellar.wine.models.Wine;
import com.cellar.wine.repositories.ProducerRepository;
import com.cellar.wine.repositories.WineRepository;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Log
@Component
public class DataLoader implements CommandLineRunner {

    private final WineRepository wineRepository;
    private final ProducerRepository producerRepository;

    public DataLoader(WineRepository wineRepository, ProducerRepository producerRepository) {
        this.wineRepository = wineRepository;
        this.producerRepository = producerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Producer producer1 = new Producer();
        producer1.setName("Mark West");
        producer1.setCountry("USA");
        producer1.setAppellation("Napa Valley");
        producerRepository.save(producer1);

        Producer producer2 = new Producer();
        producer2.setName("Clos du Vouget");
        producer2.setCountry("France");
        producer2.setAppellation("Burgundy");
        producerRepository.save(producer2);

        log.info("Loaded producers...");

        Wine wine1 = new Wine();

        wine1.setName("Brickhouse");
        wine1.setVarietal("Pinot Noir");
        wine1.setVintage("2015");
        wine1.setAppellation("Willamette Valley");
        wine1.setCountry("USA");
        wineRepository.save(wine1);

        Wine wine2 = new Wine();
        wine2.setName("Acrobat");
        wine2.setVarietal("Malbec");
        wine2.setVintage("2016");
        wine2.setAppellation("Russian River Valley");
        wine2.setCountry("USA");
        wineRepository.save(wine2);

        log.info("Loaded wines...");
    }
}
