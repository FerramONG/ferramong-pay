package ferramong.pay.models.card;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CreditCard extends Card {

    public CreditCard(String number, int cvv, String owner) {
        super(number, cvv, owner);
    }

    @Override
    public boolean pay(double value) {
        return true;
    }
}
