package spring.ws.carrentaldirectoryweb.sd.hashTable.hash.TableEntity;


import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DynamicTableStatus01 {
    private String line;
    private int id;

    public  String getPartsLineKey(){
        if (line == null) return " ";

        String[] parts = line.split(" ");
        return parts[1] + " " + parts[2] + " " + parts[3] + " " + parts[5];
    }

    public  String[] getAllPartsLine(){
        if (line == null) return new String[]{""};

        return line.split(" ");
    }

    public String getStateNumber(){
        if (line == null) return " ";

        String[] parts = line.split(" ");
        return parts[0];
    }

    public LocalDate getDate(){
        String[] parts = line.split(" ");
        return LocalDate.of(Integer.parseInt(parts[3].split("-")[0]),
                Integer.parseInt(parts[3].split("-")[1]),
                Integer.parseInt(parts[3].split("-")[2])) ;
    }

}
