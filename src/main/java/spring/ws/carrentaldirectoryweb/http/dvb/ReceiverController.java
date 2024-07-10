package spring.ws.carrentaldirectoryweb.http.dvb;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/otherController")
public class ReceiverController {
    @PostMapping(value = "/processData", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ClientResponse> processData(@RequestBody ClientResponse clientResponse) {
        // Обработка данных, полученных от первого контроллера
        // Вывод данных в консоль для проверки
        System.out.println(clientResponse.toString());

        // Возвращаем ответ
        return ResponseEntity.ok(clientResponse);
    }
}

