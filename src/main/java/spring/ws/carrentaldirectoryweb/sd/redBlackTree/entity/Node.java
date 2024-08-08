package spring.ws.carrentaldirectoryweb.sd.redBlackTree.entity;

import spring.ws.carrentaldirectoryweb.core.dto.RecordsReadDto;
import spring.ws.carrentaldirectoryweb.sd.hashTable.hash.HashService.HashTable;
import spring.ws.carrentaldirectoryweb.sd.hashTable.hash.TableEntity.DynamicTableStatus01;

import java.time.LocalDate;

public class Node {
    public LocalDate data;
    public Node right;
    public Node left;
    public Node parent;
    public HashTable hashTable;

    public boolean color; // красный - false, черный - true


    public Node(RecordsReadDto readDto, Integer hashTableSize){
        HashTable hashTable1 = new HashTable(hashTableSize);
        DynamicTableStatus01 dynamicTableStatus01 = new DynamicTableStatus01(readDto.toString(), readDto.getId());
        hashTable1.put(readDto.getMarkName(), dynamicTableStatus01);
        this.data = readDto.getFirst_date();
        this.hashTable = hashTable1;
    }

    public Node(){
    }

}
