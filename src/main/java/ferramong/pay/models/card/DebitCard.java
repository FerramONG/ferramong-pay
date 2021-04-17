package ferramong.pay.models.card;

public class DebitCard extends Card {

    public DebitCard(long number, int cvv, String owner) {
        super(number, cvv, owner);
    }

    @Override
    public boolean pay(double value) {
        return true;
    }
}
