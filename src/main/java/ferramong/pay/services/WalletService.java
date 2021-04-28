package ferramong.pay.services;

import ferramong.pay.entities.Creditools;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WalletService {

    public void newWallet(int idDweller) {
        Creditools c = Creditools.of(idDweller);

        c.newWallet(idDweller);
    }
}
