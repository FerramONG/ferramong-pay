package ferramong.pay.models.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreditoolsPayment implements Serializable {

    private int idDweller;
    private double value;
}
