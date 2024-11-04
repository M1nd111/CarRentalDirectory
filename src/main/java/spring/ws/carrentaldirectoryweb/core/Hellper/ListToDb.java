package spring.ws.carrentaldirectoryweb.core.Hellper;

import org.springframework.stereotype.Component;
import spring.ws.carrentaldirectoryweb.core.dto.RecordsReadDto;
import spring.ws.carrentaldirectoryweb.core.dto.RecordsWebDto;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListToDb {
    public static List<RecordsWebDto> list;

    public ListToDb() {
        list = new ArrayList<>();
    }

    public void addRecord(RecordsWebDto record) {
        list.add(record);
    }

    //** Ошибочка тут
    public void removeRecord(RecordsReadDto record) {
        list.remove(record);
    }

    public List<RecordsWebDto> getList() {
        return new ArrayList<>(list); // Возвращаем копию списка, чтобы избежать внешних изменений
    }

    public void clearList() {
        list.clear();
    }
}
