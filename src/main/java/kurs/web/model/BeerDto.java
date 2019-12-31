package kurs.web.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {

    @Null
    private UUID id;
    @Null
    private Integer version;

    @Null
    private OffsetDateTime creationDate;
    @Null
    private OffsetDateTime lastUpdatedDate;
    @NotBlank
    private String beerName;
    @NotNull
    private BeerStyles beerType;
    @Positive
    @NotNull
    private long UPC;
    @Positive
    @NotNull
    private BigDecimal price;
    @Positive
    @NotNull
    private Integer quantitOnHand;


}