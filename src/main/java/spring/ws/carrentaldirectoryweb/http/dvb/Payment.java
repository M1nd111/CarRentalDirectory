package spring.ws.carrentaldirectoryweb.http.dvb;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
class Payment {
    private String RRN;
    private String Date;
    private String Time;
    private String Account;
    private String Phone;
    private String Amount;
    private String Currency;
    private String Info;
}
