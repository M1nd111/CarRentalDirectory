package spring.ws.carrentaldirectoryweb.core.dto;

import lombok.Value;

import java.time.LocalTime;

@Value

public class UserCreateDto {
    LocalTime time;
    String firstname;
    String name;
    String lastname;
    String markName;
    Long applicationNumber;
}
