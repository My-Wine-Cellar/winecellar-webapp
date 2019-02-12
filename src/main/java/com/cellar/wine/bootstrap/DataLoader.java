package com.cellar.wine.bootstrap;

import com.cellar.wine.models.Producer;
import com.cellar.wine.models.Wine;
import com.cellar.wine.services.ProducerService;
import com.cellar.wine.services.WineService;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Log
@Component
@Profile("dev")
public class DataLoader implements CommandLineRunner {

    private final WineService wineService;
    private final ProducerService producerService;

    public DataLoader(WineService wineService, ProducerService producerService) {
        this.wineService = wineService;
        this.producerService = producerService;
    }

    @Override
    public void run(String... args) throws Exception {

        Producer producer1 = new Producer();
        producer1.setName("Mark West");
        producer1.setCountry("USA");
        producer1.setAppellation("Napa Valley");
        producerService.save(producer1);

        Producer producer2 = new Producer();
        producer2.setName("Big Boi");
        producer2.setCountry("France");
        producer2.setAppellation("Burgundy");
        producerService.save(producer2);

        log.info("Loaded producers...");

        Wine wine1 = new Wine();

        wine1.setLabel("Brickhouse");
        wine1.setVarietal("Pinot Noir");
        wine1.setVintage("2015");
        wine1.setProducer(producer1);
        wineService.save(wine1);

        Wine wine2 = new Wine();
        wine2.setLabel("Holla Holla");
        wine2.setVarietal("Malbec");
        wine2.setVintage("2016");
        wine2.setProducer(producer1);
        wineService.save(wine2);

        Wine wine3 = new Wine();
        wine3.setLabel("Bread and Butter");
        wine3.setVarietal("Merlot");
        wine3.setVintage("2014");
        wine3.setProducer(producer2);
        wineService.save(wine3);

        Wine wine4 = new Wine();

        wine4.setLabel("Acrobat");
        wine4.setVarietal("Chardonay");
        wine4.setVintage("2018");
        wine4.setProducer(producer2);
        wineService.save(wine4);

        producer1.getWines().add(wine1);
        producer1.getWines().add(wine2);
        producer2.getWines().add(wine3);
        producer2.getWines().add(wine4);

        log.info("Loaded wines...");

    }
}
