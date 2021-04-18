package ferramong.pay.controllers;

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

    @PostMapping(
            path = "/reward",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response reward(@RequestBody Reward reward) {
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

    @GetMapping("/reward/start/end")
    public ResponseEntity<List<Reward>> getRewards(@PathVariable("start")
                                                   @DateTimeFormat(pattern="yyyy-MM-dd") Date start,
                                                   @PathVariable("end")
                                                   @DateTimeFormat(pattern="yyyy-MM-dd") Date end) {
        return ResponseEntity.ok().body(rewardService.getAllRewardsBetween(start, end));
    }
}
