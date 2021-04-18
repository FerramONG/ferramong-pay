package ferramong.pay.services;

import ferramong.pay.entities.Payment;
import ferramong.pay.models.PaymentMethod;
import ferramong.pay.models.card.Card;
import ferramong.pay.models.card.CreditCard;
import ferramong.pay.models.card.DebitCard;
import ferramong.pay.repositories.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class PaymentService {

    private final PaymentRepository repository;

    public boolean payWithCreditCard(int idDweller, CreditCard card, double value) {
        return payWithCard(idDweller, PaymentMethod.CREDIT_CARD, card, value);
    }

    private boolean payWithCard(int idDweller, PaymentMethod method, Card card, double value) {
        if (!card.pay(value))
            return false;

        return doPayment(idDweller, method, value);
    }

    private boolean doPayment(int idDweller, PaymentMethod method, double value) {
        try {
            Payment payment = new Payment(idDweller, method, value);

            repository.save(payment);

            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean payWithDebitCard(int idDweller, DebitCard card, double value) {
        return payWithCard(idDweller, PaymentMethod.DEBIT_CARD, card, value);
    }

    public boolean payWithMoney(int idDweller, double value) {
        return doPayment(idDweller, PaymentMethod.MONEY, value);
    }

    public boolean payWithCreditools(int idDweller, double value) {
        /*Creditools wallet = Creditools.of(idDweller);

        if (wallet.getBalance() < value)
            return false;

        wallet.debit(value);
*/
        return doPayment(idDweller, PaymentMethod.CREDITOOLS, value);
    }

    public List<Payment> getAllDwellerPurchases(long idDweller) {
        return repository.getAllDwellerPurchases(idDweller);
    }

    public List<Payment> getAllOngPurchases(Date start, Date end) {
        return repository.getAllOngPurchases(start, end);
    }
}
