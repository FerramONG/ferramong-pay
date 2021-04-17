package ferramong.pay.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
public class Reward implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private long id;

    @Column(nullable = false)
    private long id_dweller;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private double value; // creditools
}