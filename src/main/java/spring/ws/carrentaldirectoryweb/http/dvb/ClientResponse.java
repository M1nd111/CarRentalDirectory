package spring.ws.carrentaldirectoryweb.http.dvb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
class ClientResponse {
    private String STAN;
    private String Response;
    private Payment Payment;
    private GetPaymentStatus GetPaymentStatus;
}
