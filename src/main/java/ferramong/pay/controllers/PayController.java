package ferramong.pay.controllers;

import ferramong.pay.entities.Payment;
import ferramong.pay.models.payment.CreditCardPayment;
import ferramong.pay.models.payment.CreditoolsPayment;
import ferramong.pay.models.payment.DebitCardPayment;
import ferramong.pay.models.payment.MoneyPayment;
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

    /**
     * Pays the ONG using credit card.
     *
     * <h2>CURL example</h2>
     * <code>
     *     curl "https://ferramong-pay.herokuapp.com/pay/credit" \
     *     -X POST \
     *     -d "{\n  \"id_dweller\": \"1\",\n  \"cardNumber\": \"1234 1234 1234 1234\", \n  \"cardCvv\":\"123\", \n  \"cardOwner\":\"FULANO DA SILVA\",\n  \"value\": \"123.34\"\n}" \
     *     -H "Content-type: application/json"
     * </code>
     *
     * @param       payment Dweller id, value to be paid and credit card
     * information
     *
     * @return      200 if ok; 404 if there is no dweller with the provided id;
     * 400 if another error occurs
     */
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

    /**
     * Pays the ONG using debit card.
     *
     * <h2>CURL example</h2>
     * <code>
     *     curl "https://ferramong-pay.herokuapp.com/pay/debit" \
     *     -X POST \
     *     -d "{\n  \"id_dweller\": \"1\",\n  \"cardNumber\": \"1234 1234 1234 1234\", \n  \"cardCvv\":\"123\", \n  \"cardOwner\":\"FULANO DA SILVA\",\n  \"value\": \"123.34\"\n}" \
     *     -H "Content-type: application/json"
     * </code>
     *
     * @param       payment Dweller id, value to be paid and credit card
     * information
     *
     * @return      200 if ok; 404 if there is no dweller with the provided id;
     * 400 if another error occurs
     */
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

    /**
     * Pays the ONG using money.
     *
     * <h2>CURL example</h2>
     * <code>
     *     curl "https://ferramong-pay.herokuapp.com/pay/money" \
     *     -X POST \
     *     -d "{\n  \"id_dweller\": \"1\",\n \"value\": \"123.34\"\n}" \
     *     -H "Content-type: application/json"
     * </code>
     *
     * @param       payment Dweller id and value to be paid
     *
     * @return      200 if ok; 404 if there is no dweller with the provided id;
     * 400 if another error occurs
     */
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

    /**
     * Pays the ONG using creditools.
     *
     * <h2>CURL example</h2>
     * <code>
     *     curl "https://ferramong-pay.herokuapp.com/pay/creditools" \
     *     -X POST \
     *     -d "{\n  \"id_dweller\": \"1\",\n \"value\": \"123.34\"\n}" \
     *     -H "Content-type: application/json"
     * </code>
     *
     * @param       payment Dweller id and value to be paid
     *
     * @return      200 if ok; 404 if there is no dweller with the provided id;
     * 400 if another error occurs
     */
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

    /**
     * Gets ONG purchases within a date range.
     *
     * <h2>CURL example</h2>
     * <code>
     *      curl "https://ferramong-pay.herokuapp.com/purchases/ong/2021-03-27/2021-03-28"
     * </code>
     *
     * @param       start Start range
     * @param       end End range
     *
     * @return      Purchases in the interval [start; end]
     */
    @GetMapping("/purchases/ong/{start}/{end}")
    public ResponseEntity<List<Payment>> getAllOngPurchases(@PathVariable("start")
                                                            @DateTimeFormat(pattern="yyyy-MM-dd") Date start,
                                                            @PathVariable("end")
                                                            @DateTimeFormat(pattern="yyyy-MM-dd") Date end) {
        return ResponseEntity.ok().body(paymentService.getAllOngPurchases(start, end));
    }

    /**
     * Gets dweller purchases.
     *
     * <h2>CURL example</h2>
     * <code>
     *      curl "https://ferramong-pay.herokuapp.com/purchases/dweller/1"
     * </code>
     *
     * @param       idDweller Dweller id
     *
     * @return      Dweller purchases
     */
    @GetMapping("/purchases/dweller/{id_dweller}")
    public ResponseEntity<List<Payment>> getAllDwellerPurchases(@PathVariable("id_dweller") int idDweller) {
        return ResponseEntity.ok().body(paymentService.getAllDwellerPurchases(idDweller));
    }
}
