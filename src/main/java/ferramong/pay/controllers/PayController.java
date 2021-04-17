package ferramong.pay.controllers;

import ferramong.pay.entities.Payment;
import ferramong.pay.entities.Reward;
import ferramong.pay.services.PaymentService;
import ferramong.pay.services.RewardService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.cors.CorsConfiguration;

import javax.ws.rs.core.Response;
import java.util.Date;

/*
* Controller
*   Deve ter minimo de logica
*	Serve para chamar serviços
*	Mapeia endpoints
* */

@RestController
@AllArgsConstructor
@CrossOrigin(
        origins = CorsConfiguration.ALL,
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
)
public class PayController {

    private final PaymentService paymentService;
    private final RewardService rewardService;

    @PostMapping(
            path = "/pay/credit",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response payOngWithCredit(@RequestBody Payment payment) {
        return schedulerService.listAllWithName(name);
    }

    @PostMapping(
            path = "/pay/debit",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response payOngWithDebit(@RequestBody Payment payment) {
        return schedulerService.listAllWithName(name);
    }

    @PostMapping(
            path = "/pay/money",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response payOngWithMoney(@RequestBody Payment payment) {
        return schedulerService.listAllWithName(name);
    }

    @PostMapping(
            path = "/pay/creditools",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response payOngWithCreditools(@RequestBody Payment payment) {
        return schedulerService.listAllWithName(name);
    }

    /*
     * id_dweller: <id>
     * value: <creditools>
     * */
    @PostMapping(
            path = "/reward",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response pay(@RequestBody Reward reward) {
        //return schedulerService.listAllWithName(name);
        return Response.noContent().build();
    }

    @GetMapping("/purchases/ong/{start}/{end}")
    public ResponseEntity<Purchase> getAllOngPurchases(@PathVariable("start")
                                                       @DateTimeFormat(pattern="yyyy-MM-dd") Date start,
                                                       @PathVariable("end")
                                                       @DateTimeFormat(pattern="yyyy-MM-dd") Date end) {
        return Response.noContent().build();
    }

    @GetMapping("/purchases/dweller/{id_dweller}")
    public ResponseEntity<Purchase> getAllDwellerPurchases(@PathVariable("id_dweller") long id_dweller) {
        return Response.noContent().build();
    }
}
