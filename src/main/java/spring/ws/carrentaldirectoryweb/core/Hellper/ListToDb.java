package spring.ws.carrentaldirectoryweb.core.Hellper;

import org.springframework.stereotype.Component;
import spring.ws.carrentaldirectoryweb.core.dto.RecordReadDto;
import spring.ws.carrentaldirectoryweb.core.dto.RecordWebDto;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListToDb {
    public static List<RecordWebDto> list;

    public ListToDb() {
        list = new ArrayList<>();
    }

    public void addRecord(RecordWebDto record) {
        list.add(record);
    }

    public void removeRecord(RecordReadDto record) {
        list.remove(record);
    }

    public List<RecordWebDto> getList() {
        return new ArrayList<>(list); // Возвращаем копию списка, чтобы избежать внешних изменений
    }

    public void clearList() {
        list.clear();
    }
}
