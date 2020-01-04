package kurs.mapper;

import kurs.domain.Beer;
import kurs.web.model.BeerDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Mapper(uses={DataMapper.class}, componentModel = "spring")
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);
    Beer beerDtoToBeer(BeerDto beerDto);
}
