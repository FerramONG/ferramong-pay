package ferramong.pay.controllers;

import ferramong.pay.entities.Payment;
import ferramong.pay.models.*;
import ferramong.pay.services.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.cors.CorsConfiguration;

import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(
        origins = CorsConfiguration.ALL,
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
)
public class PayController {

    private final PaymentService paymentService;

    @PostMapping("/pay/credit")
    public Response payOngWithCredit(@RequestBody CreditCardPayment payment) {
        boolean response = paymentService.payWithCreditCard(
                payment.getIdDweller(),
                payment.getCard(),
                payment.getValue()
        );

        return parseResponse(response);
    }

    private Response parseResponse(boolean response) {
        if (!response)
            return Response.notModified().build();

        return Response.accepted().build();
    }

    @PostMapping(
            path = "/pay/debit",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response payOngWithDebit(@RequestBody DebitCardPayment payment) {
        boolean response = paymentService.payWithDebitCard(
                payment.getIdDweller(),
                payment.getCard(),
                payment.getValue()
        );

        return parseResponse(response);
    }

    @PostMapping(
            path = "/pay/money",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response payOngWithMoney(@RequestBody MoneyPayment payment) {
        boolean response = paymentService.payWithMoney(
                payment.getId_dweller(),
                payment.getValue()
        );

        return parseResponse(response);
    }

    @PostMapping(
            path = "/pay/creditools",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response payOngWithCreditools(@RequestBody CreditoolsPayment payment) {
        boolean response = paymentService.payWithCreditools(
                payment.getId_dweller(),
                payment.getValue()
        );

        return parseResponse(response);
    }

    @GetMapping("/purchases/ong/{start}/{end}")
    public ResponseEntity<List<Payment>> getAllOngPurchases(@PathVariable("start")
                                                            @DateTimeFormat(pattern="yyyy-MM-dd") Date start,
                                                            @PathVariable("end")
                                                            @DateTimeFormat(pattern="yyyy-MM-dd") Date end) {
        return ResponseEntity.ok().body(paymentService.getAllOngPurchases(start, end));
    }

    @GetMapping("/purchases/dweller/{id_dweller}")
    public ResponseEntity<List<Payment>> getAllDwellerPurchases(@PathVariable("id_dweller") int idDweller) {
        return ResponseEntity.ok().body(paymentService.getAllDwellerPurchases(idDweller));
    }
}
