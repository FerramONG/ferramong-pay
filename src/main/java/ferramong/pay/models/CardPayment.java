package ferramong.pay.models;

import ferramong.pay.models.card.Card;
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
public abstract class CardPayment implements Serializable {

    private int idDweller;
    protected String cardNumber;
    protected int cardCvv;
    protected String cardOwner;
    private double value;

    public abstract Card getCard();
}
