package kurs.web.conttoller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kurs.domain.Beer;
import kurs.repositories.BeerRepository;
import kurs.web.model.BeerDto;
import kurs.web.model.BeerStyles;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.startsWith;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
//@ComponentScan(basePackages = "kurs.mapper")
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerRepository beerRepository;

    @Test
    void getBeer() throws Exception {
        given(beerRepository.findById(any())).willReturn(Optional.of(Beer.builder().build()));
        mockMvc.perform(get("api/v1/beer/"+UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void handlePost() throws Exception{
        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(post("api/v1/beer/")
        .contentType(MediaType.APPLICATION_JSON)
        .content(beerDtoJson))
                .andExpect(status().isCreated());
    }


    @Test
    void handleUpdate() throws Exception {
        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);
        mockMvc.perform(put("api/v1/beer/"  + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isNoContent());
    }

    @Test
    void handleDeleteBeer() throws Exception{
        mockMvc.perform(delete("api/v1/beer/"  + UUID.randomUUID().toString()))
        .andExpect(status().isNoContent());

    }
    private BeerDto getValidBeerDto() {
        return BeerDto.builder()
                .beerName("Beer Name")
                .beerType(BeerStyles.ALE)
                .price(new BigDecimal(12.95))
                .UPC(151454545L)
                .build();
    }
}
