package ferramong.pay.repositories;

import ferramong.pay.entities.Payment;
import ferramong.pay.entities.ToolsPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ToolsPaymentRepository extends JpaRepository<ToolsPayment, Integer> {

    @Query(
            "SELECT p FROM ToolsPayment p " +
            "WHERE p.id_dweller = :id_dweller"
    )
    public List<ToolsPayment> getAllDwellerPurchases(
            @Param("id_dweller") int idDweller
    );

    @Query(
            "SELECT p FROM ToolsPayment p " +
            "WHERE p.date BETWEEN :start AND :end"
    )
    public List<ToolsPayment> getAllOngPurchases(
            @Param("start") Date start,
            @Param("end") Date end
    );
}
