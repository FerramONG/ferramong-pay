package ferramong.pay.models.payment;

import ferramong.pay.models.card.DebitCard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MoneyPayment implements Serializable {

    private int id_dweller;
    private double value;
}
