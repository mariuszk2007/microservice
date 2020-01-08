package kurs.services;

import kurs.domain.Beer;
import kurs.mapper.BeerMapper;
import kurs.repositories.BeerRepository;
import kurs.web.conttoller.NewException;
import kurs.web.model.BeerDto;
import kurs.web.model.BeerStyles;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.UUID;

@Service
@Slf4j
public class BeerServicesImpl implements BeerService {

    @Autowired
    BeerRepository beerRepository;
    @Autowired
    BeerMapper beerMapper;

    @Override
    public BeerDto getBeerById(UUID beerId) {
        return beerMapper.beerToBeerDto(beerRepository.findById(beerId).orElseThrow(NewException::new));
    }

    @Override
    public BeerDto saveBeer(BeerDto beerDto) {

        return beerMapper.beerToBeerDto(beerRepository
                .save(beerMapper.beerDtoToBeer(beerDto)
                ));
    }

    @Override
    public void updateBeer(UUID beerId, BeerDto beerDto) {
        log.debug("customer id:" + beerId + " updated");
       Beer beer = beerRepository.findById(beerId).orElseThrow(NewException::new);
       beer.setBeerName(beerDto.getBeerName());
       beer.setBeerType(beerDto.getBeerType());
       beer.setPrice(beerDto.getPrice());
       beer.setUpc(beerDto.getUPC());

       beerRepository.save(beer);
    }

    @Override
    public void deleteBeer(UUID beerId) {

        log.debug("delete beer..."+ beerId);
        Beer beer = beerRepository.findById(beerId).orElseThrow(NewException::new);
        beerRepository.delete(beer);
    }
}
