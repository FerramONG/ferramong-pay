package ferramong.pay.models;

import ferramong.pay.models.card.Card;
import ferramong.pay.models.card.CreditCard;
import ferramong.pay.models.card.DebitCard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
public class DebitCardPayment extends CardPayment {

    @Override
    public Card getCard() {
        return new DebitCard(cardNumber, cardCvv, cardOwner);
    }
}
