package spring.ws.carrentaldirectoryweb.sd.hashTable.hash.HashService;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.ws.carrentaldirectoryweb.sd.list.info.ListInfo;
import spring.ws.carrentaldirectoryweb.core.dto.RecordReadDto;
import spring.ws.carrentaldirectoryweb.sd.hashTable.hash.TableEntity.DynamicTableStatus01;
import spring.ws.carrentaldirectoryweb.sd.hashTable.hash.functions.FunctionMiddleOfSquare;
import spring.ws.carrentaldirectoryweb.sd.list.DoublePointer;

import java.util.Arrays;

@Component
@NoArgsConstructor
@Slf4j
public class HashTable {
    @Getter
    @Setter
    private int filledCells = 0; // Track the number of filled cells|
    @Getter
    private Integer size = 0;
    private int m;
    @Getter
    @Setter
    private DoublePointer[] table;

    public HashTable(Integer size) {
        this.size = size;
        initTable();
    }

    public void initTable() {
        table = new DoublePointer[size];
        for (int i = 0; i < size; i++) {
            ListInfo list = new ListInfo();
            DoublePointer head = new DoublePointer();
            list.head = head;
            list.tail = head;
            head.setList(list);
            table[i] = head;
        }
        m = size;
    }

    public Boolean clearTable() {
        if (table == null) {
            return false;
        }
        table = null;
        filledCells = 0;
        return true;
    }

    public int put(String str, DynamicTableStatus01 value) {
        int k = 1;
        int key = FunctionMiddleOfSquare.getKey(str, m);

        table[key].addList(value);

        if (table[key].getSize() >= 1) {
            filledCells++;
        }

        return key;
    }

    public boolean remove(String key, DynamicTableStatus01 entity) {
        String line = entity.getLine();

        int newKey = FunctionMiddleOfSquare.getKey(key, m);

        if (table[newKey].searchByValue(entity).getData().getLine().equals(line)) {
            delete(newKey, entity);
            return true;
        } else return false;

    }

    private void delete(int key, DynamicTableStatus01 entity) {
        if (table[key].getSize() > 0) {
            table[key].listDeleteItem(entity);
        } else System.out.println("this empty");
        filledCells--;
    }


    public Integer find(String str) {
        if (str == null) return null;
        Integer key = FunctionMiddleOfSquare.getKey(str, m);

        if (table[key].getData() != null && table[key].searchByValue(str) != null) {
            return table[key].getData().getId();
        }

        return -1;
    }

    public DoublePointer find(RecordReadDto readDto) {
        Integer key = FunctionMiddleOfSquare.getKey(readDto.getStateNumber(), m);

        if (table[key].getData() != null && table[key].searchByValue(readDto.toString()) != null) {
            return table[key];
        }

        return new DoublePointer();
    }

    public Integer getKey(RecordReadDto readDto) {
        return FunctionMiddleOfSquare.getKey(readDto.getStateNumber(), m);
    }

    public void printToConsole() {
        for (int i = 0; i < table.length; i++) {
            if (table[i].getData() != null) {
                System.out.print(i);
                table[i].listPrint();
            }
        }
    }

    public String toString() {
        return "HashTable: filledCells=" + this.filledCells +
                ", size=" + this.size +
                ", m=" + this.m +
                ", table=" + Arrays.toString(this.table) ;
    }
    public void printTable() {
        System.out.println("HashTable(filledCells=" + this.filledCells +
                            ", size=" + this.size +
                            ", m=" + this.m + ")");
        for(DoublePointer pointer : table){
            if(pointer.getData() == null){
                System.out.println("\t\t\t\tnull");
            }
            else {
                System.out.print("\t\t\t\t" + pointer.getData().getStateNumber() + " -> ");
                pointer.listPrint();
            }
        }
    }
}
