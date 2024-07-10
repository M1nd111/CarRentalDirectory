package spring.ws.carrentaldirectoryweb.core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_result")
public class UserUpload implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalTime time;

    private String firstname;
    private String name;
    private String lastname;

    @Column(name = "mark_name")
    private String markName;

    @Column(name = "application_number")
    private Long applicationNumber;

    @Column(name = "first_number")
    private Integer firstNumber;

    @Column(name = "number_attempts")
    private Integer attemptsNumber;

    private Integer status;
}