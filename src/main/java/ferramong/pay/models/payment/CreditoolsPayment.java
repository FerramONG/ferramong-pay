package ferramong.pay.models.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreditoolsPayment implements Serializable {

    @NotNull private int idDweller;
    @NotNull private double value;
}
