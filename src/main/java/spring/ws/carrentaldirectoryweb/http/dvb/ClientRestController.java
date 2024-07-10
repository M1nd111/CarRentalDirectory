/*
package spring.ws.carrentaldirectoryweb.http.dvb;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import spring.dataBase.repository.entity.ClientCreateReq;

@RestController
@RequestMapping("api/cgi-bin/QIWI_XML")
public class ClientRestController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/index")
    public ResponseEntity<String> startPage(){
        return ResponseEntity.ok("dvb/cgi-bin/QIWI_XML/index");
    }

    @PostMapping(value = "/index.rb", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ClientResponse> toGateway(@ModelAttribute ClientCreateReq clientCreateReq){
        ClientResponse clientResponse = new ClientResponse();

        switch (clientCreateReq.getFunction()) {
            case "bank_account":
                clientResponse = bankAccount(clientCreateReq);
                break;
            case "bank_payment":
                clientResponse = bankPayment(clientCreateReq);
                break;
            case "getPaymentStatus":
                clientResponse = getPaymentStatus(clientCreateReq);
                break;
            default:
                clientResponse.setResponse("Invalid function");
        }

        ResponseEntity<ClientResponse> responseEntity = restTemplate.postForEntity("http://localhost:8080/api/otherController/processData", clientResponse, ClientResponse.class);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(clientResponse);
    }

    private ClientResponse bankAccount(ClientCreateReq clientCreateReq){
        return getClientResponse(clientCreateReq);
    }

    private ClientResponse bankPayment(ClientCreateReq clientCreateReq){
        return getClientResponse(clientCreateReq);
    }

    private ClientResponse getPaymentStatus(ClientCreateReq clientCreateReq){
        ClientResponse response = new ClientResponse();
        response.setSTAN(clientCreateReq.getSTAN());
        response.setResponse("OK");
        response.setGetPaymentStatus(new GetPaymentStatus(clientCreateReq.getRRN(), clientCreateReq.getDATE(),
                clientCreateReq.getTIME(), "1", clientCreateReq.getPHONE(), clientCreateReq.getAMOUNT(), clientCreateReq.getCURRENCY(), "Info"));
        return response;
    }

    private ClientResponse getClientResponse(ClientCreateReq clientCreateReq) {
        ClientResponse response = new ClientResponse();
        response.setSTAN(clientCreateReq.getSTAN());
        response.setResponse("OK");
        response.setPayment(new Payment(clientCreateReq.getRRN(), clientCreateReq.getDATE(), clientCreateReq.getTIME(),
                "1", clientCreateReq.getPHONE(), clientCreateReq.getAMOUNT(), clientCreateReq.getCURRENCY(), "Info"));
        return response;
    }
}
*/
