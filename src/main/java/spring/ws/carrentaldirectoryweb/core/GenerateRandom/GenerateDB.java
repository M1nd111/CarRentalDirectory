//package spring.ws.carrentaldirectoryweb.core.GenerateRandom;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.stereotype.Component;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.transaction.annotation.Transactional;
//import spring.ws.carrentaldirectoryweb.core.service.UserService;
//import spring.ws.carrentaldirectoryweb.core.dto.UserCreateDto;
//
//
//import java.time.LocalTime;
//import java.util.List;
//import java.util.Random;
//
//@Component
//@Slf4j
//@Transactional
//@ContextConfiguration(classes = ApplicationRunner.class)
//public class GenerateDB {
//    private static UserService userService;
//    public void generateRow(int quantity, ConfigurableApplicationContext context){
//        userService = context.getBean(UserService.class);
//        LocalTime time;
//        String[] fio;
//        String stamp;
//        Long application;
//        String firstname;
//        String name;
//        String lastname;
//        List<String> listMark = GenerateStamp.getMark();
//        List<String> listFIO = GenerateFIO.getFIO();
//        Random random = new Random();
//
//
//        for (int i = 1; i <= quantity; i++) {
//
//            time = GenerateTime.generateTime();
//            fio = listFIO.get(random.nextInt(100)).split(" ");
//            firstname = fio[0];
//            name = fio[1];
//            lastname = fio[2];
//            stamp = listMark.get(random.nextInt(50));
//            application = i+100L;
//
//            try {
//
//
//
//                UserCreateDto user = new UserCreateDto(
//                        time,
//                        firstname,
//                        name,
//                        lastname,
//                        stamp,
//                        application
//                );
//
//
//
//
//                userService.save(user);
//                log.debug("создан user " + user );
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                log.error("Exception при записи в бд");
//            }
//        }
//        log.info("Записи сохранились в бд");
//    }
//
//}
