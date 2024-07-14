package spring.ws.carrentaldirectoryweb.core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "records")
public class RecordEntity implements BaseEntity<Integer> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "state_number")
    private String stateNumber;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "mark_name")
    private String markName;

    @Column(name = "date")
    private LocalDate date;
}
