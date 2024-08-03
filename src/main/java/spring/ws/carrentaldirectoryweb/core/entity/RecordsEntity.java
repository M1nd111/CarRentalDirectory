package spring.ws.carrentaldirectoryweb.core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "records")
public class RecordsEntity implements BaseEntity<Integer> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fio")
    private String fio;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "mark_name")
    private String markName;

    @Column(name = "first_date")
    private LocalDate firstDate;
    @Column(name = "last_date")
    private LocalDate lastDate;

    @Builder.Default
    @OneToMany(mappedBy = "records")
    private List<RecordEntity> record = new ArrayList<>();

}

