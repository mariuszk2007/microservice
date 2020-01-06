package kurs;

import kurs.web.conttoller.BeerController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ApplicationTest {

    @Autowired
    BeerController beerController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(beerController).isNotNull();
    }

}