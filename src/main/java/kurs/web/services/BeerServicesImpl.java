package kurs.web.services;

import kurs.web.model.BeerDto;
import kurs.web.model.BeerStyles;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.UUID;

@Service
@Slf4j
public class BeerServicesImpl implements BeerService {
    @Override
    public BeerDto getBeerById(UUID id) {
        return BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("tyskie")
                .beerType(BeerStyles.PILSNER)
                .build();
    }

    @Override
    public BeerDto saveBeer(BeerDto beerDto) {

        return BeerDto.builder()
                .id(UUID.randomUUID())
                .build();
    }

    @Override
    public void updateBeer(UUID beerId, BeerDto beerDto) {
        log.debug("customer id:" + beerId + " updated");
    }

    @Override
    public void deleteBeer(UUID beerId) {
        log.debug("delete beer..."+ beerId);
    }
}
