package kurs.web.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import kurs.domain.Beer;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.UUID;

public class BaseTest {

    @Autowired
    ObjectMapper objectMapper;

   BeerDto getDto(){
      return  BeerDto.builder()
                .beerName("beerName")
                .beerType(BeerStyles.ALE)
                .creationDate(OffsetDateTime.now())
                .lastUpdatedDate(OffsetDateTime.now())
                .id(UUID.randomUUID())
                .price(new BigDecimal(12.99).setScale(2,RoundingMode.HALF_DOWN))
                .UPC(45456464646L)
                .minOnHands(5)
                .quantityToBrew(20)
                .build();
    }
}
