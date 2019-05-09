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
@Profile({"mysql-dev", "postgresql-dev"})
public class DataLoader implements CommandLineRunner {

    private final WineService wineService;
    private final ProducerService producerService;

    public DataLoader(WineService wineService, ProducerService producerService) {
        this.wineService = wineService;
        this.producerService = producerService;
    }

    @Override
    public void run(String... args) throws Exception {

        Producer markWest = new Producer();
        markWest.setName("Mark West");
        markWest.setCountry("USA");
        markWest.setProvince("California");
        markWest.setAppellation("Napa Valley");
        producerService.save(markWest);

        Producer bigBoi = new Producer();
        bigBoi.setName("Big Boi");
        bigBoi.setCountry("France");
        bigBoi.setProvince("Burgundy");
        bigBoi.setAppellation("Pommard");
        producerService.save(bigBoi);

        log.info("Loaded producers...");

        Wine brickhouse = new Wine();
        brickhouse.setLabel("Brickhouse");
        brickhouse.setVarietal("Pinot Noir");
        brickhouse.setVintage("2015");
        brickhouse.setProducer(markWest);
        wineService.save(brickhouse);

        Wine hollaHolla = new Wine();
        hollaHolla.setLabel("Holla Holla");
        hollaHolla.setVarietal("Malbec");
        hollaHolla.setVintage("2016");
        hollaHolla.setProducer(markWest);
        wineService.save(hollaHolla);

        Wine breadButter = new Wine();
        breadButter.setLabel("Bread and Butter");
        breadButter.setVarietal("Merlot");
        breadButter.setVintage("2014");
        breadButter.setProducer(bigBoi);
        wineService.save(breadButter);

        Wine acrobat = new Wine();
        acrobat.setLabel("Acrobat");
        acrobat.setVarietal("Chardonay");
        acrobat.setVintage("2018");
        acrobat.setProducer(bigBoi);
        wineService.save(acrobat);

        markWest.getWines().add(brickhouse);
        markWest.getWines().add(hollaHolla);
        bigBoi.getWines().add(breadButter);
        bigBoi.getWines().add(acrobat);

        log.info("Loaded wines...");

    }
}
