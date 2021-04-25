package ferramong.pay.entities;

import ferramong.pay.models.payment.PaymentMethod;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class Payment implements Serializable {

    private int id;
    private int id_dweller;
    private Date date;
    private double total;
    private String type;    // credit, debit, money, creditools

    public Payment(int id, int idDweller, String type, double value, Date date) {
        this.id = id;
        id_dweller = idDweller;
        this.date = date;
        total = value;
        this.type = type;
    }
}
