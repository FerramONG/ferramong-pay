package ferramong.pay.models.card;

public class CreditCard extends Card {

    public CreditCard(long number, int cvv, String owner) {
        super(number, cvv, owner);
    }

    @Override
    public boolean pay(double value) {
        return true;
    }
}
