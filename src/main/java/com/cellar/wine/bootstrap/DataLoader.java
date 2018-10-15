package com.cellar.wine.bootstrap;

import com.cellar.wine.model.Wine;
import com.cellar.wine.model.Producer;
import com.cellar.wine.services.ProducerService;
import com.cellar.wine.services.WineService;
import com.cellar.wine.services.map.ProducerServiceMap;
import com.cellar.wine.services.map.WineServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DataLoader implements CommandLineRunner {

    private final WineService wineService;
    private final ProducerService producerService;

    public DataLoader(WineService wineService, ProducerService producerService) {
        this.wineService = wineService;
        this.producerService = producerService;
    }

    @Override
    public void run(String... args) throws Exception {

        Wine wine1 = new Wine();
        wine1.setId(1L);
        wine1.setAppellation("Willamette Valley");
        wine1.setCountry("USA");
        wine1.setName("Brickhouse");
        wine1.setVarietal("Pinot");

        wineService.save(wine1);

        Wine wine2 = new Wine();
        wine2.setId(2L);
        wine2.setAppellation("Walla Walla");
        wine2.setCountry("USA");
        wine2.setName("Holla Holla");
        wine2.setVarietal("Malbec");

        wineService.save(wine2);

        System.out.println("Loaded wines...");

        Producer producer1 = new Producer();
        producer1.setId(1L);
        producer1.setName("Mark West");
        producer1.setCountry("USA");
        producer1.setAppellation("Napa Valley");

        producerService.save(producer1);

        Producer producer2 = new Producer();
        producer2.setId(2L);
        producer2.setName("Big Boi");
        producer2.setCountry("France");
        producer2.setAppellation("Burgundy");

        producerService.save(producer2);

        System.out.println("Loaded producers...");

    }
}
