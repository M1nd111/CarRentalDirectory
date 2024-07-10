package spring.ws.carrentaldirectoryweb.core.dto;

import java.time.LocalDate;


public record UserDto(
                String username,
                LocalDate birth_date,
                String firstname,
                String lastname,
                String role,
                Integer company_id
) {
}
