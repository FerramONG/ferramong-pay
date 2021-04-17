package ferramong.pay.services;

import ferramong.pay.entities.Reward;
import ferramong.pay.repositories.RewardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class RewardService {

    private final RewardRepository repository;

    public boolean rewardDweller(Reward reward) {
        try {
            repository.save(reward);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public List<Reward> getAllRewardsBetween(Date start, Date end) {
        return repository.getAllRewardsBetween(start, end);
    }
}
