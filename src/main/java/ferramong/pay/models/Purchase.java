package ferramong.pay.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Currency;
import java.util.Date;

@Entity
@Getter
@Setter
public class Purchase implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private long id;

    @Column(nullable = false)
    private int id_dweller;

    @Column(nullable = false)
    private Currency amount;

    @Column(nullable = false)
    private Date date;
}
