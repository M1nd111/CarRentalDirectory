package spring.ws.carrentaldirectoryweb.core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Year;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "record")
public class RecordEntity implements BaseEntity<Integer> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "model_name")
    private String modelName;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "state_number")
    private String stateNumber;

    @Column(name = "year")
    private Integer year;

    @ManyToOne
    @JoinColumn(name = "records_id")
    private RecordsEntity records;
}
