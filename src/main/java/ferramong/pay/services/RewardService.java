package ferramong.pay.services;

import ferramong.pay.models.payment.CreditoolsPayment;
import ferramong.pay.entities.Reward;
import ferramong.pay.entities.Creditools;
import ferramong.pay.repositories.RewardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class RewardService {

    private final RewardRepository repository;

    public boolean rewardDweller(CreditoolsPayment reward) {

        Creditools wallet = Creditools.of(reward.getIdDweller());

        if (!wallet.creditUsingCreditools(reward.getValue()))
            return false;

        return doReward(reward);
    }

    private boolean doReward(CreditoolsPayment reward) {
        try {
            repository.save(new Reward(reward.getIdDweller(), reward.getValue()));

            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public List<Reward> getAllRewardsBetween(Date start, Date end) {
        return repository.getAllRewardsBetween(start, end);
    }

    public List<Reward> getDwellerRewards(int idDweller) {
        return repository.getDwellerRewards(idDweller);
    }
}
