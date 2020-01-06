package kurs.web.conttoller;

import kurs.domain.Beer;
import kurs.mapper.BeerMapper;
import kurs.repositories.BeerRepository;
import kurs.web.model.BeerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping("api/v1/beer")
@RestController
public class BeerController {

   // @Autowired
  //  private BeerService beerService;

    @Autowired
   private  BeerRepository beerRepository;
    @Autowired
   private BeerMapper beerMapper;

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId){


        return new ResponseEntity(beerMapper.beerToBeerDto(beerRepository.findById(beerId).get()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity handlePost(@Valid @RequestBody BeerDto beerDto){
       // BeerDto savedBeerDto = beerService.saveBeer(beerDto);
        Beer savedBeer = beerRepository.save(beerMapper.beerDtoToBeer(beerDto));



        return new ResponseEntity( HttpStatus.CREATED);


    }
    @PutMapping("/{beerId}")
    public ResponseEntity handleUpdate(@PathVariable("beerId") UUID beerId,@Valid @RequestBody BeerDto beerDto){

       //  beerService.updateBeer(beerId, beerDto);
        beerRepository.findById(beerId).ifPresent(beer->{
        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerType(beerDto.getBeerType());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUPC());

        beerRepository.save(beer);

        });
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleDeleteBeer(@PathVariable("beerId") UUID beerId){
       // beerService.deleteBeer(beerId);
        beerRepository.findById(beerId).ifPresent(beer->
        beerRepository.delete(beer));

    }

}
