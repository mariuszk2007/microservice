package kurs.services;

import kurs.web.model.BeerDto;


import java.util.UUID;

public interface BeerService {
   BeerDto getBeerById(UUID id);

    BeerDto saveBeer(BeerDto beerDto);

    void updateBeer(UUID beerId, BeerDto beerDto);

    void deleteBeer(UUID beerId);
}
