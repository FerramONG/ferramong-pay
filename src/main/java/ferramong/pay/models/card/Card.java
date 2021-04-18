package ferramong.pay.models.card;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public abstract class Card implements Serializable {

    protected String number;
    protected int cvv;
    protected String owner;

    protected Card(String number, int cvv, String owner) {
        this.number = number;
        this.cvv = cvv;
        this.owner = owner;
    }

    public abstract boolean pay(double value);
}
