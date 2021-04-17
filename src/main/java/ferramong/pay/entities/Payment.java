package ferramong.pay.entities;

import ferramong.pay.models.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private long id;

    @Column(nullable = false)
    private int id_dweller;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private double total;

    @Column(nullable = false)
    private String type;    // credit, debit, money, creditools

    public Payment(int idDweller, PaymentMethod type, double value) {
        id_dweller = idDweller;
        date = new Date();
        total = value;
        this.type = type.name();
    }
}
