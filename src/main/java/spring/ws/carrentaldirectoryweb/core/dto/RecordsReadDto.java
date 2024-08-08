package spring.ws.carrentaldirectoryweb.core.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Data
public class RecordsReadDto {
    private Integer id;
//    @Pattern(regexp = "^[A-ZА-Я]{1}[0-9]{3}[A-ZА-Я]{2}$", message = "Некорректный госномер")
//    @Size(min = 6, max = 6, message = "Госномер должен быть длиной 6 символов")
    private String fio;

    @Pattern(regexp = "^\\+7[0-9]{10}$", message = "Некорректный номер телефона")
    private String phoneNumber;

    private String markName;

    @NotNull(message = "Дата не может быть пустой")
    @FutureOrPresent(message = "Дата не может быть меньше текущей")
    private LocalDate first_date;

    private LocalDate last_date;

    @Override
    public String toString() {
        return this.fio + " " + this.getPhoneNumber() + " " + this.markName + " "
                + this.first_date + " " + this.last_date;
    }
}