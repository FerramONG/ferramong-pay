package ferramong.pay.repositories;

import ferramong.pay.entities.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.xml.ws.Response;
import java.util.Date;
import java.util.List;

public interface RewardRepository extends JpaRepository<Reward, Integer> {

    @Query(
            "SELECT r FROM Reward r " +
            "WHERE r.date BETWEEN :start AND :end"
    )
    public List<Reward> getAllRewardsBetween(
            @Param("start") Date start,
            @Param("end") Date end
    );
}
