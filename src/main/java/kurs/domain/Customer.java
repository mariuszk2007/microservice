package kurs.domain;


import lombok.*;

import javax.persistence.Entity;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Customer {

    private UUID customerId;
    private String customerName;
}
