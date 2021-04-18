package ferramong.pay.models.card;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public abstract class Card implements Serializable {

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
