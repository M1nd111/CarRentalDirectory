package spring.ws.carrentaldirectoryweb.sd.list;

import lombok.*;
import spring.ws.carrentaldirectoryweb.core.Hellper.DebugMessage;
import spring.ws.carrentaldirectoryweb.core.Hellper.ListToDb;
import spring.ws.carrentaldirectoryweb.core.Hellper.SearchMessage;
import spring.ws.carrentaldirectoryweb.core.dto.RecordsWebDto;
import spring.ws.carrentaldirectoryweb.sd.list.info.ListInfo;
import spring.ws.carrentaldirectoryweb.core.dto.RecordsReadDto;
import spring.ws.carrentaldirectoryweb.sd.hashTable.hash.TableEntity.DynamicTableStatus01;

import java.time.LocalDate;
import java.util.Objects;

public class DoublePointer {
    private DoublePointer previous;
    private DoublePointer next;
    @Getter
    @Setter
    private DynamicTableStatus01 data;
    @Setter
    private ListInfo list;
    @Getter
    private Integer size = 0;

    public DoublePointer() {
        previous = null;
        next = null;
    }


    public DoublePointer listGet(int DoublePointerNumber) {
        DoublePointer current = list.head;
        for (int i = 0; i < DoublePointerNumber; i++) {
            current = current.next;

        }
        return current;
    }

    public DoublePointer searchByValue(DynamicTableStatus01 value) {
        DoublePointer point = list.head;
        DoublePointer search = null;

        while (point != null && !Objects.equals(search != null ? search.data.getLine() : null, value.getLine())) {
            if (Objects.equals(point.data.getLine(), value.getLine())) {
                search = point;
            }
            point = point.next;
        }

        return search;
    }

    public DoublePointer searchByValue(String line) {
        DoublePointer point = list.head;
        DoublePointer search = null;

        while (point != null && !Objects.equals(search != null ? search.data.getLine() : null, line)) {
            if (Objects.equals(point.data.getLine(), line)) {
                search = point;
            }
            point = point.next;
        }

        return search;
    }


    public void addList(DynamicTableStatus01 value) {
        DoublePointer item = new DoublePointer();
        DoublePointer point;
        item.data = value;

        if (list == null || list.head == null || list.head.data == null) {

            list.head.data = value;

        } else {
            point = list.head;

//            if (point.data.getDate().isBefore(item.data.getDate())) {
                item.next = point;
                point.previous = item;
                list.head = item;
//            } else {
//                while (point.next != null && item.data.getDate().isBefore(point.next.data.getDate())) {
//                    point = point.next;
//                }
//                if (point.next != null) {
//                    point.next.previous = item;
//                }
//
//                item.next = point.next;
//                point.next = item;
//                item.previous = point;
//            }

            list.tail = list.head;
            while (list.tail.next != null) {
                list.tail = list.tail.next;
            }
        }
        item.list = list;
        size++;
    }


    public DoublePointer ListGet(int DoublePointerNumber) {
        DoublePointer current = list.head;
        for (int i = 0; i < DoublePointerNumber; i++) {
            current = current.next;

        }
        return current;
    }

    public void listDeleteItem(int deleteNumber) {
        DoublePointer numb = ListGet(deleteNumber - 1);

        if (deleteNumber == 1) {
            list.head = list.head.next;
            if (list.head != null) {
                list.head.previous = null;
            }
        } else if (numb.next != null) {
            numb.next.previous = numb.previous;
            numb.previous.next = numb.next;
        } else {
            numb.previous.next = null;
            list.tail = numb.previous;
        }

    }

    public boolean listDeleteItem(DynamicTableStatus01 item) {
        String line = item.getLine();
        DoublePointer current = list.head;
        while (!current.getData().getLine().equals(line)){
            if (current.next == null){
                return false;
            }
            current = current.next;
        }
        if (current.previous == null) {
            list.head = list.head.next;
            if (list.head != null) {
                list.head.previous = null;
            }
        } else if (current.next != null) {
            current.next.previous = current.previous;
            current.previous.next = current.next;
        } else {
            current.previous.next = null;
            list.tail = current.previous;
        }
        current = list.head;
        while(current.next != null){
            current.list = list;
            current = current.next;
        }
        size--;
        return true;
    }

    public ListInfo listDeleteItem(RecordsReadDto item) {
        String line = item.toString();
        DoublePointer current = list.head;
        while (!current.getData().getLine().equals(line)){
            if (current.next == null){
                return list;
            }
            current = current.next;
        }
        if(current.previous == null && current.next == null){
            current = null;
            list.head = new DoublePointer();
            list.tail = list.head;
            return list;
        }
        else if (current.previous == null) {
            list.head = list.head.next;
            if (list.head != null) {
                list.head.previous = null;
            }
        } else if (current.next != null) {
            current.next.previous = current.previous;
            current.previous.next = current.next;
        } else {
            current.previous.next = null;
            list.tail = current.previous;
        }

        current = list.head;
        current.list = list;
        while(current.next != null){
            current.list = list;
            current = current.next;
        }

        size--;
        return list;
    }


    public void listDelete() {
        while(list.head.next != null) {
            list.head = list.head.next;
            list.head.next = null;
        }
        list.head = null;
        list.tail = null;
        System.out.println("Список удален");
    }


    public void listPrint() {
        if (list == null){
            System.out.println("Список не инициализирован");
        }
        if (list.head == null) {
            System.out.println("Отсутствуют элементы в списке");
            return;
        }

        DoublePointer current = list.head;

        while (current != null) {
            System.out.print(current.getData().getLine() + " | ");
            DebugMessage.message += current.getData().getLine() + " | ";
            current = current.next;
        }

        System.out.println();
        DebugMessage.message += "\n";
    }

    public String searchByValueForMark(String line) {
        DoublePointer point = list.head;
        String search = null;
        SearchMessage.step = 0;
        while (point != null && !Objects.equals(search, line)) {
            SearchMessage.step++;
            if (Objects.equals(point.data.getStateNumber(), line)) {
                search = point.data.getLine();
            }
            point = point.next;
        }

        return search;
    }

    public void addToDbFromList() {
        if (list == null) {
            System.out.println("Список не инициализирован");
            return;
        }
        if (list.head == null) {
            System.out.println("Отсутствуют элементы в списке");
            return;
        }

        DoublePointer current = list.head;

        while (current != null) {
            String[] line = current.getData().getAllPartsLine();
            String[] time1 = line[5].split("-");
            LocalDate localDate1 = LocalDate.of(Integer.parseInt(time1[0]), Integer.parseInt(time1[1]), Integer.parseInt(time1[2]));
            String[] time2 = line[6].split("-");
            LocalDate localDate2 = LocalDate.of(Integer.parseInt(time2[0]), Integer.parseInt(time2[1]), Integer.parseInt(time2[2]));

            RecordsWebDto readDto = RecordsWebDto.builder()
                    .fio(line[0] + " " + line[1] + " " +line[2])
                    .phoneNumber(line[3])
                    .markName(line[4])
                    .firsDate(localDate1)
                    .lastDate(localDate2)
                    .build();

            ListToDb.list.add(readDto);
            current = current.next;
        }

        System.out.println();
    }
}
