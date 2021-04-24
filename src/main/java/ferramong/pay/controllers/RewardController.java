package ferramong.pay.controllers;

import ferramong.pay.models.payment.CreditoolsPayment;
import ferramong.pay.entities.Reward;
import ferramong.pay.services.RewardService;
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
public class RewardController {

    private final RewardService rewardService;

    /**
     * Rewards a dweller.
     *
     * <h2>CURL example</h2>
     * <code>
     *     curl "https://ferramong-pay.herokuapp.com/reward" \
     *     -X POST \
     *     -d "{\n  \"idDweller\": \"1\",\n \"value\": \"123.34\"\n}" \
     *     -H "Content-type: application/json"
     * </code>
     *
     * @param       reward Dweller id and value to be rewarded
     *
     * @return      200 if ok; 404 if there is no dweller with the provided id;
     * 400 if another error occurs
     */
    @PostMapping(
            path = "/reward",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response reward(@RequestBody CreditoolsPayment reward) {
        boolean response = rewardService.rewardDweller(reward);

        return parseResponse(response);
    }

    private Response parseResponse(boolean response) {
        if (!response)
            return Response.notModified().build();

        return Response.accepted().build();
    }

    @GetMapping("/reward/dweller/{id_dweller}")
    public ResponseEntity<List<Reward>> getRewards(@PathVariable("id_dweller") int idDweller) {
        return ResponseEntity.ok().body(rewardService.getDwellerRewards(idDweller));
    }

    /**
     * Gets rewards within a date range.
     *
     * <h2>CURL example</h2>
     * <code>
     *      curl "https://ferramong-pay.herokuapp.com/reward/2021-03-27/2021-03-28"
     * </code>
     *
     * @param       start Start range
     * @param       end End range
     *
     * @return      Rewards in the interval [start; end]
     */
    @GetMapping("/reward/{start}/{end}")
    public ResponseEntity<List<Reward>> getRewards(@PathVariable("start")
                                                   @DateTimeFormat(pattern="yyyy-MM-dd") Date start,
                                                   @PathVariable("end")
                                                   @DateTimeFormat(pattern="yyyy-MM-dd") Date end) {
        return ResponseEntity.ok().body(rewardService.getAllRewardsBetween(start, end));
    }
}
