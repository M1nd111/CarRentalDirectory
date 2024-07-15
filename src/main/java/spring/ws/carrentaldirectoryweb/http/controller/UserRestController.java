package spring.ws.carrentaldirectoryweb.http.controller;

import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.annotation.SessionScope;
import spring.ws.carrentaldirectoryweb.core.Hellper.ListToDb;
import spring.ws.carrentaldirectoryweb.core.dto.RecordReadDto;
import spring.ws.carrentaldirectoryweb.core.dto.RecordWebDto;
import spring.ws.carrentaldirectoryweb.core.entity.RecordEntity;
import spring.ws.carrentaldirectoryweb.core.repository.RecordRepository;
import spring.ws.carrentaldirectoryweb.core.service.RecordService;
import spring.ws.carrentaldirectoryweb.sd.redBlackTree.RedBlackTree;
import spring.ws.carrentaldirectoryweb.sd.redBlackTree.info.Info;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@SessionAttributes("redBlackTree")
public class UserRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RecordService recordService;
    @Autowired
    RecordRepository recordRepository;

    @GetMapping("/all")
    public List<RecordReadDto> getAllRecords(HttpSession session) {
        ListToDb list = new ListToDb();
        list.clearList();
        recordService.delAll();
        var redBlackTree = (RedBlackTree) session.getAttribute("redBlackTree");
        redBlackTree.addAllRecords(Info.root, 0);

        for(RecordWebDto recordWebDto : ListToDb.list){
            recordService.addEntity(recordWebDto);
        }

        return recordService.findAll(); // Реализуйте метод для получения всех записей
    }

    @PostMapping("/create")
    public String create(@RequestParam("size") Integer size,
                         HttpSession session){
        RedBlackTree redBlackTree = new RedBlackTree(size);
        session.setAttribute("redBlackTree", redBlackTree);
        logger.info("RB HT SIZE: {}", redBlackTree.getHashTableSize());
        return "{\"status\":\"success\"}";
    }

    @PostMapping("/add")
    public String add(@RequestParam("stateNumber") String stateNumber,
                      @RequestParam("phoneNumber") String phoneNumber,
                      @RequestParam("markName") String markName,
                      @RequestParam("date") LocalDate date,
                      HttpSession session){
        var redBlackTree = (RedBlackTree) session.getAttribute("redBlackTree");
        redBlackTree.insertNode(RecordReadDto.builder()
                .id(0)
                .stateNumber(stateNumber)
                .phoneNumber(phoneNumber)
                .markName(markName)
                .date(date)
                .build());
        session.setAttribute("redBlackTree", redBlackTree);
        redBlackTree.printLinesTree(Info.root, 0);
        logger.info("RB HT SIZE: {}", redBlackTree.getHashTableSize());
        return "{\"status\":\"success\"}";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("stateNumber") String stateNumber,
                         @RequestParam("phoneNumber") String phoneNumber,
                         @RequestParam("markName") String markName,
                         @RequestParam("date") LocalDate date,
                         HttpSession session){
        var redBlackTree = (RedBlackTree) session.getAttribute("redBlackTree");

        redBlackTree.deleteNode(RecordReadDto.builder()
                .id(0)
                .stateNumber(stateNumber)
                .phoneNumber(phoneNumber)
                .markName(markName)
                .date(date)
                .build());

        session.setAttribute("redBlackTree", redBlackTree);
        redBlackTree.printLinesTree(Info.root, 0);
        logger.info("RB HT SIZE: {}", redBlackTree.getHashTableSize());
        return "{\"status\":\"success\"}";
    }

}
