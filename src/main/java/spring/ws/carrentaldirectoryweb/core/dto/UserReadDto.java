package spring.ws.carrentaldirectoryweb.core.dto;

import lombok.Value;

import java.time.LocalTime;

@Value
public class UserReadDto {
        int id;
        LocalTime time;
        String firstname;
        String name;
        String lastname;
        String markName;
        Long applicationNumber;

    public String toString() {
        return this.getTime() + " " + this.getFirstname() + " " + this.getName() + " "
                + this.getLastname() + " " + this.getMarkName() + " " + this.getApplicationNumber();
    }
}
