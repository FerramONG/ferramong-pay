package ferramong.pay.models;

import ferramong.pay.models.card.CreditCard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreditCardPayment implements Serializable {

    private int id_dweller;
    private CreditCard card;
    private double value;
}
