package ferramong.pay.repositories;

import ferramong.pay.entities.Payment;
import ferramong.pay.entities.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    @Query(
            "SELECT p FROM Payment p " +
            "WHERE p.id_dweller = :id_dweller"
    )
    public List<Payment> getAllDwellerPurchases(
            @Param("id_dweller") int id_dweller
    );

    @Query(
            "SELECT p FROM Payment p " +
            "WHERE p.date BETWEEN :start AND :end"
    )
    public List<Payment> getAllOngPurchases(
            @Param("start") Date start,
            @Param("end") Date end
    );
}
