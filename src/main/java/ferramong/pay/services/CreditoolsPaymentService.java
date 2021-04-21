package ferramong.pay.services;

import ferramong.pay.entities.Creditools;
import ferramong.pay.entities.Payment;
import ferramong.pay.models.card.Card;
import ferramong.pay.entities.CreditoolsPayment;
import ferramong.pay.models.payment.PaymentMethod;
import ferramong.pay.repositories.CreditoolsPaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class CreditoolsPaymentService {

    private final CreditoolsPaymentRepository repository;

    public boolean buyWithCreditCard(int idDweller, Card card, double value) {
        return payWithCard(idDweller, PaymentMethod.CREDIT_CARD, card, value);
    }

    private boolean payWithCard(int idDweller, PaymentMethod method, Card card, double value) {
        if (!card.pay(value))
            return false;

        return doPayment(idDweller, method, value);
    }

    private boolean doPayment(int idDweller, PaymentMethod method, double value) {
        try {
            CreditoolsPayment payment = new CreditoolsPayment(idDweller, method, value);

            repository.save(payment);

            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean buyWithDebitCard(int idDweller, Card card, double value) {
        return payWithCard(idDweller, PaymentMethod.DEBIT_CARD, card, value);
    }

    public boolean buyWithMoney(int idDweller, double value) {
        return doPayment(idDweller, PaymentMethod.MONEY, value);
    }

    public boolean payWithCreditools(int idDweller, double value) {
        Creditools wallet = Creditools.of(idDweller);

        if (wallet.getBalance() < value)
            return false;

        if (!wallet.debit(value))
            return false;

        return doPayment(idDweller, PaymentMethod.CREDITOOLS, value);
    }

    public List<Payment> getAllDwellerPurchases(int idDweller) {
        return generatePaymentListOf(repository.getAllDwellerPurchases(idDweller));
    }

    private List<Payment> generatePaymentListOf(List<CreditoolsPayment> payments) {
        List<Payment> purchases = new ArrayList<>();

        for (CreditoolsPayment purchase : payments) {
            purchases.add(purchase.getPayment());
        }

        return purchases;
    }

    public List<Payment> getAllOngPurchases(Date start, Date end) {
        return generatePaymentListOf(repository.getAllOngPurchases(start, end));
    }
}
