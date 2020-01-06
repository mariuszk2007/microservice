package kurs.bootstrap;

import kurs.domain.Beer;
import kurs.web.model.BeerStyles;
import kurs.repositories.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class BeerBootstrap implements CommandLineRunner {


    @Autowired
    private final BeerRepository beerrepository;

    public BeerBootstrap(BeerRepository beerrepository) {
        this.beerrepository = beerrepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(beerrepository.count()==0){
        loadBeerObjects();
            System.out.println("you have :" +beerrepository.count() + "in beer repository");
        }
    }

    private void loadBeerObjects() {

            beerrepository.save(Beer.builder()
                    .beerName("Tyskie")
                    .beerType(BeerStyles.PILSNER)
                    .price(new BigDecimal("12.95"))
                    .quantityToBrew(200)
                    .upc(3751456L)
                    .minOnHands(12)
                    .build());
            beerrepository.save(Beer.builder()
                    .beerName("Lech")
                    .beerType(BeerStyles.ALE)
                    .price(new BigDecimal("11.95"))
                    .quantityToBrew(300)
                    .upc(456987963L)
                    .minOnHands(12)
                    .build());
        }

}
