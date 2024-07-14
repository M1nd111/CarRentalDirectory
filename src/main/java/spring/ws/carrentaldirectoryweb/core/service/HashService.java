//package spring.ws.carrentaldirectoryweb.core.service;
//
//
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.stereotype.Service;
//import org.springframework.test.context.ContextConfiguration;
//import spring.ws.carrentaldirectoryweb.core.repository.UserRepository;
//import spring.ws.carrentaldirectoryweb.core.dto.UserReadDto;
//import spring.ws.carrentaldirectoryweb.sd.hashTable.hash.TableEntity.DynamicTableStatus01;
//import spring.ws.carrentaldirectoryweb.sd.hashTable.hash.HashService.HashTable;
//
//
//@Service
//@ContextConfiguration(classes = ApplicationRunner.class)
//public class HashService {
//
//    private static UserService userService;
//    private static UserRepository userRepository;
//
//    public void FillingAll(HashTable hashTable, ConfigurableApplicationContext context) {
//        userService = context.getBean(UserService.class);
//        var list = userService.findAll();
//        for (UserReadDto it : list){
//            hashTable.put(
//                    it.getFirstname()
//                            + " " + it.getName()
//                            + " " + it.getLastname()
//                            + " " + it.getApplicationNumber(),
//
//                            DynamicTableStatus01.builder().id(it.getId()).line(it.toString()).build());
//        }
//    }
//}
