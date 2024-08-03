package spring.ws.carrentaldirectoryweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootApplication
@ConfigurationPropertiesScan
public class CarRentalDirectoryWebApplication {
    public static void main(String[] args) {
        var context = SpringApplication.run(CarRentalDirectoryWebApplication.class, args);

    }

}


//RecordRepository repository = context.getBean(RecordRepository.class);
//Random random = new Random();
//
//        for (int i = 0; i < 19; i++) {
//        repository.save(RecordEntity.builder()
//                            .stateNumber("A" + random.nextInt(100, 999) + "AA")
//        .phoneNumber("+7" +random.nextLong(1000000000L, 9999999999L))
//        .markName("TEST")
//                            .date(LocalDate.now()
//                                    .minusMonths(random.nextInt(0, 10))
//        .minusDays(random.nextInt(0, 10)))
//        .build());
//        }