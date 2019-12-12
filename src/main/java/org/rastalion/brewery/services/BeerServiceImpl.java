package org.rastalion.brewery.services;

import lombok.extern.log4j.Log4j2;
import org.rastalion.brewery.web.model.BeerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Log4j2
@Service
public class BeerServiceImpl implements BeerService {
    @Override
    public BeerDto getBeerById(UUID beerId) {
        log.info("Get beer by id: {}", beerId);

        return BeerDto.builder().beerId(UUID.randomUUID())
                .beerName("Augustijn")
                .beerStyle("Darkness my old friend!")
                .build();
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        log.info("Saved: {}", beerDto);

        return BeerDto.builder()
                .beerId(UUID.randomUUID())
                .build();
    }

    @Override
    public void updateBeer(UUID beerId, BeerDto beerDto) {
        log.info("Change requested: {}", beerDto);
        // Todo: impl, would add a real impl to update beer from db or some other resource
    }

    @Override
    public void deleteById(UUID beerId) {
        log.info("Request for deleting a beer with id: {}", beerId);
        // Todo: impl, would delete a beer from db or some other resource
    }
}
