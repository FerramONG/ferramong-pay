package ferramong.pay.models.payment;

import ferramong.pay.models.card.Card;
import ferramong.pay.models.card.DebitCard;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DebitCardPayment extends CardPayment {

    @Override
    public Card getCard() {
        return new DebitCard(cardNumber, cardCvv, cardOwner);
    }
}
