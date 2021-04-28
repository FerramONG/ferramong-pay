package ferramong.pay.controllers;

import ferramong.pay.entities.Payment;
import ferramong.pay.models.payment.CreditCardPayment;
import ferramong.pay.models.payment.DebitCardPayment;
import ferramong.pay.models.payment.MoneyPayment;
import ferramong.pay.services.CreditoolsPaymentService;
import ferramong.pay.services.WalletService;
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
public class CreditoolsPaymentController {

    private final CreditoolsPaymentService creditoolsPaymentService;
    private final WalletService creditoolsService;

    /**
     * Buys creditools using credit card.
     *
     * <h2>CURL example</h2>
     * <code>
     *     curl "https://ferramong-pay.herokuapp.com/buy/creditools/credit" \
     *     -X POST \
     *     -d "{\n  \"idDweller\": \"1\",\n  \"cardNumber\": \"1234 1234 1234 1234\", \n  \"cardCvv\":\"123\", \n  \"cardOwner\":\"FULANO DA SILVA\",\n  \"value\": \"123.34\"\n}" \
     *     -H "Content-type: application/json"
     * </code>
     *
     * @param       payment Dweller id, value to be paid and credit card
     * information
     *
     * @return      200 if ok; 404 if there is no dweller with the provided id;
     * 400 if another error occurs
     */
    @PostMapping("/buy/creditools/credit")
    public Response payOngWithCredit(@RequestBody CreditCardPayment payment) {
        if (payment.getIdDweller() <= 0)
            creditoolsService.newWallet(payment.getIdDweller());

        boolean response = creditoolsPaymentService.buyWithCreditCard(
                payment.getIdDweller(),
                payment.getCard(),
                payment.getValue()
        );

        return parseResponse(response);
    }

    private Response parseResponse(boolean response) {
        if (!response)
            return Response.status(407).build();

        return Response.accepted().build();
    }

    /**
     * Buys creditools using debit card.
     *
     * <h2>CURL example</h2>
     * <code>
     *     curl "https://ferramong-pay.herokuapp.com/buy/creditools/debit" \
     *     -X POST \
     *     -d "{\n  \"idDweller\": \"1\",\n  \"cardNumber\": \"1234 1234 1234 1234\", \n  \"cardCvv\":\"123\", \n  \"cardOwner\":\"FULANO DA SILVA\",\n  \"value\": \"123.34\"\n}" \
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
            path = "/buy/creditools/debit",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response payOngWithDebit(@RequestBody DebitCardPayment payment) {
        if (payment.getIdDweller() <= 0)
            creditoolsService.newWallet(payment.getIdDweller());

        boolean response = creditoolsPaymentService.buyWithDebitCard(
                payment.getIdDweller(),
                payment.getCard(),
                payment.getValue()
        );

        return parseResponse(response);
    }

    /**
     * Buys creditools using money.
     *
     * <h2>CURL example</h2>
     * <code>
     *     curl "https://ferramong-pay.herokuapp.com/buy/creditools/money" \
     *     -X POST \
     *     -d "{\n  \"idDweller\": \"1\",\n \"value\": \"123.34\"\n}" \
     *     -H "Content-type: application/json"
     * </code>
     *
     * @param       payment Dweller id and value to be paid
     *
     * @return      200 if ok; 404 if there is no dweller with the provided id;
     * 400 if another error occurs
     */
    @PostMapping(
            path = "/buy/creditools/money",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response payOngWithMoney(@RequestBody MoneyPayment payment) {
        if (payment.getIdDweller() <= 0)
            creditoolsService.newWallet(payment.getIdDweller());

        boolean response = creditoolsPaymentService.buyWithMoney(
                payment.getIdDweller(),
                payment.getValue()
        );

        return parseResponse(response);
    }

    /**
     * Gets ONG creditools purchases within a date range.
     *
     * <h2>CURL example</h2>
     * <code>
     *      curl "https://ferramong-pay.herokuapp.com/purchases/creditools/ong/2021-03-27/2021-03-28"
     * </code>
     *
     * @param       start Start range
     * @param       end End range
     *
     * @return      Purchases in the interval [start; end]
     */
    @GetMapping("/purchases/creditools/ong/{start}/{end}")
    public ResponseEntity<List<Payment>> getAllOngCreditoolsPurchases(@PathVariable("start")
                                                                      @DateTimeFormat(pattern="yyyy-MM-dd") Date start,
                                                                      @PathVariable("end")
                                                                      @DateTimeFormat(pattern="yyyy-MM-dd") Date end) {
        return ResponseEntity.ok().body(creditoolsPaymentService.getAllOngPurchases(start, end));
    }

    /**
     * Gets dweller creditools purchases.
     *
     * <h2>CURL example</h2>
     * <code>
     *      curl "https://ferramong-pay.herokuapp.com/purchases/creditools/dweller/1"
     * </code>
     *
     * @param       idDweller Dweller id
     *
     * @return      Dweller purchases
     */
    @GetMapping("/purchases/creditools/dweller/{id_dweller}")
    public ResponseEntity<List<Payment>> getAllDwellerCreditoolsPurchases(@PathVariable("id_dweller") int idDweller) {
        return ResponseEntity.ok().body(creditoolsPaymentService.getAllDwellerPurchases(idDweller));
    }
}
