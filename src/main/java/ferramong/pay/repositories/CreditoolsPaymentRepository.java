package ferramong.pay.repositories;

import ferramong.pay.entities.CreditoolsPayment;
import ferramong.pay.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CreditoolsPaymentRepository extends JpaRepository<CreditoolsPayment, Integer> {

    @Query(
            "SELECT p FROM CreditoolsPayment p " +
            "WHERE p.id_dweller = :id_dweller"
    )
    public List<CreditoolsPayment> getAllDwellerPurchases(
            @Param("id_dweller") int idDweller
    );

    @Query(
            "SELECT p FROM CreditoolsPayment p " +
            "WHERE p.date BETWEEN :start AND :end"
    )
    public List<CreditoolsPayment> getAllOngPurchases(
            @Param("start") Date start,
            @Param("end") Date end
    );
}
