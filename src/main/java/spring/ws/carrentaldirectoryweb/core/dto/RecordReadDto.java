package spring.ws.carrentaldirectoryweb.core.dto;


import lombok.*;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Data
public class RecordReadDto {
    private Integer id;
    private String stateNumber;
    private String phoneNumber;
    private String markName;
    private LocalDate date;

    public String toString(){
        return this.stateNumber + " " + this.getPhoneNumber() + " " + this.markName + " " + this.date;
    }

}
