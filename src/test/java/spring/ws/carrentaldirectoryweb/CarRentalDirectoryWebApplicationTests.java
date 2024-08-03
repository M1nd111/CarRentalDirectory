package spring.ws.carrentaldirectoryweb;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import spring.ws.carrentaldirectoryweb.sd.redBlackTree.RedBlackTree;
import spring.ws.carrentaldirectoryweb.core.dto.RecordReadDto;
import spring.ws.carrentaldirectoryweb.sd.redBlackTree.info.Info;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = CarRentalDirectoryWebApplication.class)
class CarRentalDirectoryWebApplicationTests {

    @Test
    void mainTest() {

        Integer hashTableSize = 3;
        RedBlackTree redBlackTree = new RedBlackTree(hashTableSize);

        RecordReadDto readDto = RecordReadDto.builder()
                .id(1)
                .date(LocalDate.of(2024,7,30))
                .markName("Subaru")
                .phoneNumber("+79834270216")
                .stateNumber("A300BB")
                .build();

        RecordReadDto readDto1 = RecordReadDto.builder()
                .id(1)
                .date(LocalDate.of(2023,8,8))
                .markName("Subaru")
                .phoneNumber("+79149818482")
                .stateNumber("A300AB")
                .build();

        RecordReadDto readDto2 = RecordReadDto.builder()
                .id(1)
                .date(LocalDate.of(2022,2,11))
                .markName("Subaru")
                .phoneNumber("+79149818482")
                .stateNumber("A311BB")
                .build();

        RecordReadDto readDto3 = RecordReadDto.builder()
                .id(1)
                .date(LocalDate.of(2021,12,12))
                .markName("Subaru")
                .phoneNumber("+79149818482")
                .stateNumber("A311CC")
                .build();

        RecordReadDto readDto4 = RecordReadDto.builder()
                .id(1)
                .date(LocalDate.of(2024,11,11))
                .markName("Subaru")
                .phoneNumber("+79149818482")
                .stateNumber("A311CD")
                .build();



        redBlackTree.printLinesTree(Info.root, 0);

        redBlackTree.insertNode(readDto);
        redBlackTree.insertNode(readDto1);
        redBlackTree.insertNode(readDto2);
        readDto2.setStateNumber("A311BA");
        redBlackTree.insertNode(readDto2);
//        redBlackTree.insertNode(readDto3);
//        redBlackTree.insertNode(readDto4);


        System.out.println("\n\n");
        redBlackTree.printStruct(Info.root, 0);

        System.out.println("\n");
        redBlackTree.printLinesTree(Info.root, 0);
        System.out.println("\n");

        System.out.println("Поиск");
        redBlackTree.printLinesTreeWithPeriodForDate(Info.root, "A300AB",
                LocalDate.of(2024,1,1), LocalDate.of(2024,12,12));

        redBlackTree.deleteNode(readDto);
//        redBlackTree.deleteNode(readDto1);
//        redBlackTree.deleteNode(readDto2);
//
//        System.out.println("\n\n");
//        RedBlackTree.printTree(Info.root, 0);
        Boolean finish = true;
        assertEquals(finish, true);
    }

}
