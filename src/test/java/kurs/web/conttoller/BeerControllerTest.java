package kurs.web.conttoller;

import com.fasterxml.jackson.databind.ObjectMapper;

import kurs.domain.Beer;
import kurs.repositories.BeerRepository;
import kurs.web.model.BeerDto;
import kurs.web.model.BeerStyles;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.snippet.Attributes.key;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
@AutoConfigureRestDocs
@ComponentScan(basePackages = "kurs.mapper")
@ExtendWith({RestDocumentationExtension.class})
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
        mockMvc.perform(get("/api/v1/beer/{beerId}",UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("api/v1/beer-get",
                pathParameters (
                        parameterWithName("beerId").description("UUID of desired beer to get.")
                ),
              /*  requestParameters(
                        parameterWithName("iscold").description("Is Beer Cold Query param")
                ),*/
                responseFields(
                        fieldWithPath("id").description("Id of Beer"),
                        fieldWithPath("version").description("Version number"),
                        fieldWithPath("creationDate").description("Date Created"),
                        fieldWithPath("lastUpdatedDate").description("Date Updated"),
                        fieldWithPath("beerName").description("Beer Name"),
                        fieldWithPath("beerType").description("Beer Style"),
                        fieldWithPath("upc").description("UPC of Beer"),
                        fieldWithPath("price").description("Price"),
                        fieldWithPath("minOnHands").description("Quantity On hand"),
                        fieldWithPath("quantityToBrew").description("Quantity to Brew")
                )));

    }



    @Test
    void handlePost() throws Exception{
        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);
        ConstrainedFields fields = new ConstrainedFields(BeerDto.class);

        mockMvc.perform(post("/api/v1/beer/")
        .contentType(MediaType.APPLICATION_JSON)
        .content(beerDtoJson))
                .andExpect(status().isCreated())
        .andDo(document("api/v1/beer-new",
               requestFields(
                       fields.withPath("id").ignored(),
                       fields.withPath("version").ignored(),
                       fields.withPath("creationDate").ignored(),
                       fields.withPath("lastUpdatedDate").ignored(),
                       fields.withPath("beerName").description("Neme of the beer"),
                       fields.withPath("beerType").description("Type of the beer"),
                       fields.withPath("upc").description("Beer UPC").attributes(),
                       fields.withPath("price").description("Beer Price"),
                       fields.withPath("minOnHands").ignored(),
                       fields.withPath("quantityToBrew").ignored()
                       )
               ) );
    }


    @Test
    void handleUpdate() throws Exception {
        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);
        ConstrainedFields fields = new ConstrainedFields(BeerDto.class);
        mockMvc.perform(put("/api/v1/beer/{beerId}",UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isNoContent())
                .andDo(document("api/v1/beer-update",
                                pathParameters (
                                        parameterWithName("beerId").description("UUID of desired beer to get.")
                                ),
                        requestFields(
                                fields.withPath("id").ignored(),
                                fields.withPath("version").ignored(),
                                fields.withPath("creationDate").ignored(),
                                fields.withPath("lastUpdatedDate").ignored(),
                                fields.withPath("beerName").description("Neme of the beer"),
                                fields.withPath("beerType").description("Type of the beer"),
                                fields.withPath("upc").description("Beer UPC").attributes(),
                                fields.withPath("price").description("Beer Price"),
                                fields.withPath("minOnHands").ignored(),
                                fields.withPath("quantityToBrew").ignored()
                        )
                ));
    }

    @Test
    void handleDeleteBeer() throws Exception{
        mockMvc.perform(delete("/api/v1/beer/"  + UUID.randomUUID().toString()))
        .andExpect(status().isNoContent());

    }
    private BeerDto getValidBeerDto() {
        return BeerDto.builder()
                .beerName("Beer Name")
                .beerType(BeerStyles.ALE)
                .price(new BigDecimal(12.95).setScale(2,RoundingMode.HALF_DOWN))
                .UPC(151454545L)
                .build();
    }
    private static class ConstrainedFields {

        private final ConstraintDescriptions constraintDescriptions;

        ConstrainedFields(Class<?> input) {
            this.constraintDescriptions = new ConstraintDescriptions(input);
        }

        private FieldDescriptor withPath(String path) {
            return fieldWithPath(path).attributes(key("constraints").value(StringUtils
                    .collectionToDelimitedString(this.constraintDescriptions
                            .descriptionsForProperty(path), ". ")));
        }
    }
}
