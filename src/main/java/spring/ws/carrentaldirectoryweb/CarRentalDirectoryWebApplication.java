package spring.ws.carrentaldirectoryweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import spring.ws.carrentaldirectoryweb.sd.redBlackTree.RedBlackTree;
import spring.ws.carrentaldirectoryweb.sd.redBlackTree.entity.RecordReadDto;
import spring.ws.carrentaldirectoryweb.sd.redBlackTree.info.Info;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootApplication
@ConfigurationPropertiesScan
public class CarRentalDirectoryWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarRentalDirectoryWebApplication.class, args);


    }

}
