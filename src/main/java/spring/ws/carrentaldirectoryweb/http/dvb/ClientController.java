/*
package spring.ws.carrentaldirectoryweb.http.dvb;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;


@Controller
@RequestMapping("/cgi-bin/QIWI_XML")
@CrossOrigin
public class ClientController {
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/index")
    public String startPage(){
        return "dvb/cgi-bin/QIWI_XML/index";
    }

    @PostMapping("/index.rb")
    public ResponseEntity<String> ToGateway(Model model, ClientCreateReq clientCreateReq ){
        model.addAttribute("clientReq", clientCreateReq);

        String xmlResponse = "";

        switch (clientCreateReq.getFunction()){

            case "bank_account":
                xmlResponse = bankAccount(clientCreateReq);
                break;
            case "bank_payment":
                xmlResponse = bankPayment(clientCreateReq);
                break;
            case "getPaymentStatus":
                xmlResponse = getPaymentStatus(clientCreateReq);
                break;

        }

        // Отправляем XML-ответ
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(xmlResponse);

    }
    private String bankAccount(ClientCreateReq clientCreateReq){
        System.out.println(clientCreateReq.toString());

        String xmlResponse =
                "<?xml version=\"1.0\" encoding=\"utf-8\" ?>" +
                "<XML>" +
                "<mBilling Version=\"1.0\">" +
                "<STAN>" + clientCreateReq.getSTAN() + "</STAN>" +
                "<Response>OK</Response>" +
                "<Payment>" +
                "<RRN>" + clientCreateReq.getRRN() + "</RRN>" +
                "<Date>" + clientCreateReq.getDATE() + "</Date>" +
                "<Time>" + clientCreateReq.getTIME() + "</Time>" +
                "<Account>1</Account>" +
                "<Phone>" + clientCreateReq.getPHONE() + "</Phone>" +
                "<Amount>" + clientCreateReq.getAMOUNT() + "</Amount>" +
                "<Currency>" + clientCreateReq.getCURRENCY() + "</Currency>" +
                "<Info>Info</Info>" +
                "</Payment>" +
                "</mBilling>" +
                "</XML>";

        return xmlResponse;

    }
    private String bankPayment(ClientCreateReq clientCreateReq){
        System.out.println(clientCreateReq.toString());

        String xmlResponse =
                "<?xml version=\"1.0\" encoding=\"utf-8\" ?>" +
                        "<XML>" +
                        "<mBilling Version=\"1.0\">" +
                        "<STAN>" + clientCreateReq.getSTAN() + "</STAN>" +
                        "<Response>OK</Response>" +
                        "<Payment>" +
                        "<RRN>" + clientCreateReq.getRRN() + "</RRN>" +
                        "<Date>" + clientCreateReq.getDATE() + "</Date>" +
                        "<Time>" + clientCreateReq.getTIME() + "</Time>" +
                        "<Account>1</Account>" +
                        "<Phone>" + clientCreateReq.getPHONE() + "</Phone>" +
                        "<Amount>" + clientCreateReq.getAMOUNT() + "</Amount>" +
                        "<Currency>" + clientCreateReq.getCURRENCY() + "</Currency>" +
                        "<Info>Info</Info>" +
                        "</Payment>" +
                        "</mBilling>" +
                        "</XML>";

        return xmlResponse;

    }
    private String getPaymentStatus(ClientCreateReq clientCreateReq){
        System.out.println(clientCreateReq.toString());

        String xmlResponse =
                "<?xml version=\"1.0\" encoding=\"utf-8\" ?>" +
                        "<XML>" +
                        "<mBilling Version=\"1.0\">" +
                        "<STAN>" + clientCreateReq.getSTAN() + "</STAN>" +
                        "<Response>OK</Response>" +
                        "<getPaymentStatus>" +
                        "<RRN>" + clientCreateReq.getRRN() + "</RRN>" +
                        "<Date>" + clientCreateReq.getDATE() + "</Date>" +
                        "<Time>" + clientCreateReq.getTIME() + "</Time>" +
                        "<Account>1</Account>" +
                        "<Phone>" + clientCreateReq.getPHONE() + "</Phone>" +
                        "<Amount>" + clientCreateReq.getAMOUNT() + "</Amount>" +
                        "<Currency>" + clientCreateReq.getCURRENCY() + "</Currency>" +
                        "<Info>Info</Info>" +
                        "</getPaymentStatus>" +
                        "</mBilling>" +
                        "</XML>";

        return xmlResponse;
    }
}





//@PostMapping("/index.rb")
//public void ToGateway(Model model, ClientCreateReq clientCreateReq ){
//    model.addAttribute("clientReq", clientCreateReq);
//
//    String url = "http://localhost:8080/api/dvb";
//
//    var headers = new HttpHeaders();
//    headers.setContentType(MediaType.APPLICATION_JSON);
//
//    HttpEntity<ClientCreateReq> requestEntity = new HttpEntity<>(clientCreateReq, headers);
//
//    String response = restTemplate.postForObject(url, requestEntity, String.class);
//
//}
*/
