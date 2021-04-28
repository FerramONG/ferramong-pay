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

    public boolean hasWallet(int idDweller) {
        Creditools wallet = Creditools.of(idDweller);

        try {
            wallet.getBalance();
        }
        catch (Throwable e) {
            return false;
        }

        return true;
    }

    public double getBalance(int idDweller) {
        Creditools wallet = Creditools.of(idDweller);

        return wallet.getBalance();
    }

    public boolean debit(int idDweller, double value) {
        Creditools wallet = Creditools.of(idDweller);

        return wallet.debit(value);
    }
}
