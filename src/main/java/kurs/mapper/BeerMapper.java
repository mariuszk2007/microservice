package kurs.mapper;

import kurs.domain.Beer;
import kurs.web.model.BeerDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Component
@Mapper(uses={DataMapper.class})
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);
    Beer beerDtoToBeer(BeerDto beerDto);
}
