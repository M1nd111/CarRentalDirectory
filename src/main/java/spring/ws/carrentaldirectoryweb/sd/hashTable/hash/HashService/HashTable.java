package spring.ws.carrentaldirectoryweb.sd.hashTable.hash.HashService;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import spring.ws.carrentaldirectoryweb.core.Hellper.DebugMessage;
import spring.ws.carrentaldirectoryweb.sd.list.info.ListInfo;
import spring.ws.carrentaldirectoryweb.core.dto.RecordsReadDto;
import spring.ws.carrentaldirectoryweb.sd.hashTable.hash.TableEntity.DynamicTableStatus01;
import spring.ws.carrentaldirectoryweb.sd.hashTable.hash.functions.FunctionMiddleOfSquare;
import spring.ws.carrentaldirectoryweb.sd.list.DoublePointer;

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

    public DoublePointer find(RecordsReadDto readDto) {
        Integer key = FunctionMiddleOfSquare.getKey(readDto.getMarkName(), m);

        if (table[key].getData() != null && table[key].searchByValue(readDto.toString()) != null) {
            return table[key];
        }

        return new DoublePointer();
    }

    public String returnByMarkName(String markName)  {
        Integer key = FunctionMiddleOfSquare.getKey(markName, m);

        if (table[key].getData() != null && table[key].searchByValue(markName) != null) {
            return table[key].searchByValueForMark(markName);
        }

        return "";
    }

    public Boolean findMarkName(String markName)  {
        Integer key = FunctionMiddleOfSquare.getKey(markName, m);

        for (int i = 0; i < this.size; i++) {
            if(table[key].getData() != null && table[key].searchByValue(markName) != null) return true;
        }
        return false;
    }

    public Integer getKey(RecordsReadDto readDto) {
        return FunctionMiddleOfSquare.getKey(readDto.getMarkName(), m);
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
                ", size=" + this.size;
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

    public void printTable(Integer len) {
        System.out.println("HashTable(filledCells=" + this.filledCells +
                ", size=" + this.size +
                ", m=" + this.m + ")");
        DebugMessage.message += "HashTable(filledCells=" + this.filledCells +
                ", size=" + this.size +
                ", m=" + this.m + ")\n";
        int it = 0;
        for(DoublePointer pointer : table){

            if(pointer.getData() == null){
                for (int i = 0; i < len; i++) {

                    System.out.print("              ");
                    DebugMessage.message += "--------------";
                }
                System.out.println(it + ":" +"null");
                DebugMessage.message += it + ":" + "null\n";
            }
            else {
                for (int i = 0; i < len; i++) {

                    System.out.print("              ");
                    DebugMessage.message += "--------------";
                }
                System.out.print(it + ":" + " -> ");
                DebugMessage.message += it + ":" + " -> ";
                pointer.listPrint();

            }
            ++it;
        }
        System.out.println();
        DebugMessage.message += "\n";
    }

    public void addToDb() {
        for(DoublePointer pointer : table){
            if(pointer.getData() != null){
                pointer.addToDbFromList();
            }
        }
    }

}
