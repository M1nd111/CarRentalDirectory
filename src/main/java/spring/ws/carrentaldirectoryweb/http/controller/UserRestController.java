package spring.ws.carrentaldirectoryweb.http.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spring.ws.carrentaldirectoryweb.core.Hellper.DebugMessage;
import spring.ws.carrentaldirectoryweb.core.Hellper.ListToDb;
import spring.ws.carrentaldirectoryweb.core.Hellper.SearchMessage;
import spring.ws.carrentaldirectoryweb.core.dto.RecordsReadDto;
import spring.ws.carrentaldirectoryweb.core.dto.RecordsWebDto;
import spring.ws.carrentaldirectoryweb.core.repository.RecordRepository;
import spring.ws.carrentaldirectoryweb.core.repository.RecordsRepository;
import spring.ws.carrentaldirectoryweb.core.service.RecordService;
import spring.ws.carrentaldirectoryweb.core.service.RecordsService;
import spring.ws.carrentaldirectoryweb.sd.redBlackTree.RedBlackTree;
import spring.ws.carrentaldirectoryweb.sd.redBlackTree.info.Info;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@SessionAttributes("redBlackTree")
public class UserRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RecordsService recordsService;
    @Autowired
    RecordsRepository recordRepository;

    @GetMapping("/all")
    public List<RecordsReadDto> getAllRecords(HttpSession session) {
        ListToDb list = new ListToDb();
        list.clearList();
        recordsService.delAll();
        var redBlackTree = (RedBlackTree) session.getAttribute("redBlackTree");
        redBlackTree.addAllRecords(Info.root, 0);

        for(RecordsWebDto recordsWebDto : ListToDb.list){
            recordsService.addEntity(recordsWebDto);
        }

        return recordsService.findAll(); // Реализуйте метод для получения всех записей
    }

    @PostMapping("/search")
    public ResponseEntity<Map<String, Object>> search(@RequestParam("markName") String markName,
                                                      @RequestParam("startDate") LocalDate startDate,
                                                      @RequestParam("endDate") LocalDate endDate,
                                                      HttpSession session) {

        Map<String, Object> response = new HashMap<>();

        var redBlackTree = (RedBlackTree) session.getAttribute("redBlackTree");

        SearchMessage searchMessage = new SearchMessage();
        searchMessage.clearMessage();
        searchMessage = new SearchMessage();

        redBlackTree.printLinesTreeWithPeriodForDate(Info.root, markName, startDate, endDate);

        try {
            // Ваш метод debug, который возвращает результат
            String result = searchMessage.getString() + "\n\nШагов поиска: " + SearchMessage.step;
            response.put("status", "success");
            response.put("result", result);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/debug")
    public ResponseEntity<Map<String, Object>> debug(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        var redBlackTree = (RedBlackTree) session.getAttribute("redBlackTree");
        DebugMessage debugMessage = new DebugMessage();
        debugMessage.clearMessage();
        debugMessage = new DebugMessage();
        redBlackTree.printStruct(Info.root, 0);
        try {
            // Ваш метод debug, который возвращает результат
            String result = debugMessage.getString();
            response.put("status", "success");
            response.put("result", result);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
        }
        return ResponseEntity.ok(response);
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
    public String add(@Valid @ModelAttribute RecordsReadDto recordReadDto,
                      HttpSession session){
        var redBlackTree = (RedBlackTree) session.getAttribute("redBlackTree");
        recordReadDto.setId(0);
        redBlackTree.insertNode(recordReadDto);
        session.setAttribute("redBlackTree", redBlackTree);
        redBlackTree.printLinesTree(Info.root, 0);
        logger.info("RB HT SIZE: {}", redBlackTree.getHashTableSize());
        return "{\"status\":\"success\"}";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("fio") String fio,
                         @RequestParam("phoneNumber") String phoneNumber,
                         @RequestParam("markName") String markName,
                         @RequestParam("first_date") LocalDate firstDate,
                         @RequestParam("last_date") LocalDate lastDate,
                         HttpSession session){
        var redBlackTree = (RedBlackTree) session.getAttribute("redBlackTree");

        redBlackTree.deleteNode(RecordsReadDto.builder()
                .id(0)
                .fio(fio)
                .phoneNumber(phoneNumber)
                .markName(markName)
                .first_date(firstDate)
                .last_date(lastDate)
                .build());

        session.setAttribute("redBlackTree", redBlackTree);
        redBlackTree.printLinesTree(Info.root, 0);
        logger.info("RB HT SIZE: {}", redBlackTree.getHashTableSize());
        return "{\"status\":\"success\"}";
    }
    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> uploadFile(@RequestParam("file") MultipartFile file, HttpSession session) {
        Map<String, Object> response = new HashMap<>();

        if (file.isEmpty()) {
            response.put("status", "error");
            response.put("message", "Файл пустой");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            List<RecordsReadDto> records = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
            String line;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length != 7) {
                    // Обработка некорректных строк
                    continue;
                }

                String fio = parts[0] + " " + parts[1] + " " + parts[2];
                String phoneNumber = parts[3];
                String markName = parts[4];
                LocalDate firstDate = LocalDate.parse(parts[5], formatter);
                LocalDate lastDate = LocalDate.parse(parts[6], formatter);

                RecordsReadDto record = RecordsReadDto.builder()
                        .id(0)
                        .fio(fio)
                        .phoneNumber(phoneNumber)
                        .markName(markName)
                        .first_date(firstDate)
                        .last_date(lastDate)
                        .build();

                records.add(record);
            }

            // Добавление записей в redBlackTree
            var redBlackTree = (RedBlackTree) session.getAttribute("redBlackTree");
            for (RecordsReadDto record : records) {
                redBlackTree.insertNode(record);
            }
            session.setAttribute("redBlackTree", redBlackTree);

            // Обновление данных
            List<RecordsReadDto> allRecords = getAllRecords(session);
            response.put("status", "success");
            response.put("message", "Файл успешно загружен и обработан");
            response.put("data", allRecords);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
        }

        return ResponseEntity.ok(response);
    }
    @PostMapping("/export")
    public ResponseEntity<String> exportData(HttpSession session) {
        String downloadsPath = "C:/Users/maks_/OneDrive/Рабочий стол/";
        String fileName = "car_records.txt";

        try {
            List<RecordsReadDto> records = recordsService.findAll();
            StringBuilder fileContent = new StringBuilder();

            for (RecordsReadDto record : records) {
                fileContent.append(record.getFio())
                        .append(" ")
                        .append(record.getPhoneNumber())
                        .append(" ")
                        .append(record.getMarkName())
                        .append(" ")
                        .append(record.getFirst_date())
                        .append(" ")
                        .append(record.getLast_date())
                        .append("\n");
            }

            // Запись в файл
            Path filePath = Paths.get(downloadsPath + fileName);
            Files.writeString(filePath, fileContent.toString());

            // Формируем ссылку для скачивания
            String downloadLink = "/api/users/download/" + fileName;

            return ResponseEntity.ok(downloadLink);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при сохранении файла: " + e.getMessage());
        }
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        try {
            Path filePath = Paths.get("C:/Users/maks_/OneDrive/Рабочий стол/" + fileName);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
