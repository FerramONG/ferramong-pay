package ferramong.pay.models.card;

public abstract class Card {

    protected long number;
    protected int cvv;
    protected String owner;

    protected Card(long number, int cvv, String owner) {
        this.number = number;
        this.cvv = cvv;
        this.owner = owner;
    }

    public abstract boolean pay(double value);
}
