package spring.ws.carrentaldirectoryweb.http.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.ws.carrentaldirectoryweb.core.dto.RecordReadDto;
import spring.ws.carrentaldirectoryweb.core.repository.RecordRepository;
import spring.ws.carrentaldirectoryweb.core.service.RecordService;
import spring.ws.carrentaldirectoryweb.sd.redBlackTree.RedBlackTree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RecordService recordService;
    @Autowired
    RecordRepository recordRepository;

    @Autowired
    RedBlackTree redBlackTree;

    @PostMapping("/create")
    public String create(@RequestParam("size") Integer size, Model model){
        redBlackTree = new RedBlackTree(size);
        model.addAttribute("redBlackTree", redBlackTree);
        logger.info("RB HT SIZE: {}", redBlackTree.getHashTableSize());
        return "{\"status\":\"success\"}";
    }

    @PostMapping("/add")
    public String add(@RequestParam("entity") RecordReadDto readDto, Model model){
        redBlackTree = (RedBlackTree) model.getAttribute("redBlackTree");

        redBlackTree.insertNode(readDto);

        model.addAttribute("redBlackTree", redBlackTree);
        logger.info("RB HT SIZE: {}", redBlackTree.getHashTableSize());
        return "{\"status\":\"success\"}";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("entity") RecordReadDto readDto, Model model){
        redBlackTree = (RedBlackTree) model.getAttribute("redBlackTree");

        redBlackTree.deleteNode(readDto);

        model.addAttribute("redBlackTree", redBlackTree);
        logger.info("RB HT SIZE: {}", redBlackTree.getHashTableSize());
        return "{\"status\":\"success\"}";
    }
}

