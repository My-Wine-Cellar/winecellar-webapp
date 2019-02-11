package com.cellar.wine.bootstrap;

import com.cellar.wine.models.Producer;
import com.cellar.wine.models.Wine;
import com.cellar.wine.security.Authority;
import com.cellar.wine.security.AuthorityRepository;
import com.cellar.wine.security.AuthoritySerivce;
import com.cellar.wine.security.UserService;
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
    private final UserService userService;
    private final AuthoritySerivce authoritySerivce;
    private final AuthorityRepository authorityRepository;

    public DataLoader(WineService wineService, ProducerService producerService, UserService userService, AuthorityRepository authorityRepository, AuthoritySerivce authoritySerivce) {
        this.wineService = wineService;
        this.producerService = producerService;
        this.userService = userService;
        this.authorityRepository = authorityRepository;
        this.authoritySerivce = authoritySerivce;
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

        Authority adminAuth = new Authority();
        adminAuth.setAuthority("ADMIN");
        adminAuth.setUsername("ADMIN");
        authoritySerivce.createAuthority(adminAuth);

        Authority userAuth = new Authority();
        userAuth.setAuthority("USER");
        userAuth.setUsername("USER");
        authoritySerivce.createAuthority(userAuth);

//        User admin = new User();
//        admin.setEnabled(true);
//        admin.setUsername("admin");
//        admin.setPassword("password");
//        Authority adminRole = authorityRepository.findByAuthority("ADMIN");
//        admin.setAuthorities(Collections.singleton(adminRole));
//        userService.createAdmin(admin);
//
//        User user = new User();
//        user.setEnabled(true);
//        user.setUsername("user");
//        user.setPassword("password");
//        Authority userRole = authorityRepository.findByAuthority("USER");
//        user.setAuthorities(Collections.singleton(userRole));
//        userService.createUser(user);

    }
}
