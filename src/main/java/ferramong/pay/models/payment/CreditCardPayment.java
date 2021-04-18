package ferramong.pay.models.payment;

import ferramong.pay.models.card.Card;
import ferramong.pay.models.card.CreditCard;

public class CreditCardPayment extends CardPayment {

    @Override
    public Card getCard() {
        return new CreditCard(cardNumber, cardCvv, cardOwner);
    }
}
