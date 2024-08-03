package spring.ws.carrentaldirectoryweb.core.dto;


import lombok.*;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Data
public class RecordsWebDto {
    private String fio;
    private String phoneNumber;
    private String markName;
    private LocalDate firsDate;
    private LocalDate lastDate;

//    public String toString(){
//        return this.stateNumber + " " + this.getPhoneNumber() + " " + this.markName + " " + this.date;
//    }

}
