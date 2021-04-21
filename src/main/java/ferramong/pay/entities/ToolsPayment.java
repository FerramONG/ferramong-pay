package ferramong.pay.entities;

import ferramong.pay.models.payment.PaymentMethod;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ToolsPayment implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private int id;

    @Column(nullable = false)
    private int id_dweller;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private double total;

    @Column(nullable = false)
    private String type;    // credit, debit, money, creditools

    public ToolsPayment(int idDweller, PaymentMethod type, double value) {
        id_dweller = idDweller;
        date = new Date();
        total = value;
        this.type = type.name();
    }

    public Payment getPayment() {
        return new Payment(id, id_dweller, type, total, date);
    }
}
