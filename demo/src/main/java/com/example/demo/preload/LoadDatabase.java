package com.example.demo.preload;

import com.example.demo.domain.Actor;
import com.example.demo.domain.Cinema;
import com.example.demo.domain.Country;
import com.example.demo.domain.Genre;
import com.example.demo.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CountryRepository countryRepository,
                                   ActorRepository actorRepository, GenreRepository genreRepository,
                                   CinemaRepository cinemaRepository) {

        return args -> {
            log.info("Preloading countries");
            log.info("Preloading " + countryRepository.save(new Country("Швеция")));
            log.info("Preloading " + countryRepository.save(new Country("Китай")));
            log.info("Preloading actors");
            log.info("Preloading " + actorRepository.save(new Actor("Финн", "Вулфхард", "", 19)));
            log.info("Preloading " + actorRepository.save(new Actor("Дэвид", "Харбор", "", 46)));
            log.info("Preloading genres");
            log.info("Preloading " + genreRepository.save(new Genre("Комедия")));
            log.info("Preloading " + genreRepository.save(new Genre("Боевик")));
            log.info("Preloading cinemas");
            log.info("Preloading " + cinemaRepository.save(new Cinema("Золотой дракон", "карибских пиратов, 3")));
            log.info("Preloading " + cinemaRepository.save(new Cinema("Синий дракон", "уральских рабочих, 16")));
        };
    }
}
