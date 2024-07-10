package spring.ws.carrentaldirectoryweb;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import spring.ws.carrentaldirectoryweb.sd.redBlackTree.RedBlackTree;
import spring.ws.carrentaldirectoryweb.sd.redBlackTree.entity.RecordReadDto;
import spring.ws.carrentaldirectoryweb.sd.redBlackTree.info.Info;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CarRentalDirectoryWebApplicationTests {

    @Test
    void mainTest() {
        Integer hashTableSize = 10;
        RedBlackTree redBlackTree = new RedBlackTree(hashTableSize);

        RecordReadDto readDto = RecordReadDto.builder()
                .id(1)
                .date(LocalDate.now())
                .markName("test")
                .phoneNumber("test")
                .stateNumber("test")
                .build();

        RecordReadDto readDto1 = RecordReadDto.builder()
                .id(1)
                .date(LocalDate.of(8,8,8))
                .markName("test1")
                .phoneNumber("test1")
                .stateNumber("test1")
                .build();

        RecordReadDto readDto2 = RecordReadDto.builder()
                .id(1)
                .date(LocalDate.of(10,10,10))
                .markName("test2")
                .phoneNumber("test2")
                .stateNumber("test2")
                .build();

        RecordReadDto readDto3 = RecordReadDto.builder()
                .id(1)
                .date(LocalDate.of(12,12,12))
                .markName("test3")
                .phoneNumber("test3")
                .stateNumber("test3")
                .build();

        RecordReadDto readDto4 = RecordReadDto.builder()
                .id(1)
                .date(LocalDate.of(11,11,11))
                .markName("test4")
                .phoneNumber("test4")
                .stateNumber("test4")
                .build();

        redBlackTree.insertNode(readDto);
        readDto.setStateNumber("newHash");
        redBlackTree.insertNode(readDto);
        redBlackTree.insertNode(readDto1);
        redBlackTree.insertNode(readDto2);
        redBlackTree.insertNode(readDto3);
        redBlackTree.insertNode(readDto4);

        System.out.println("\n\n");
        RedBlackTree.printTree(Info.root, 0);

//        System.out.println("\n");
//        RedBlackTree.printLinesTree(Info.root, 0);
        System.out.println("\n");

        RedBlackTree.printLinesTreeWithPeriodForDate(Info.root, 0,
                LocalDate.of(9,1,1), LocalDate.of(11,12,1));

//        redBlackTree.deleteNode(readDto);
//        redBlackTree.deleteNode(readDto1);
//        redBlackTree.deleteNode(readDto2);
//
//        System.out.println("\n\n");
//        RedBlackTree.printTree(Info.root, 0);
        Boolean finish = true;
        assertEquals(finish, true);

    }

}
