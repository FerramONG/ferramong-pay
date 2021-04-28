package ferramong.pay.services;

import ferramong.pay.entities.Creditools;
import ferramong.pay.entities.CreditoolsPayment;
import ferramong.pay.entities.Payment;
import ferramong.pay.entities.ToolsPayment;
import ferramong.pay.models.card.Card;
import ferramong.pay.models.payment.PaymentMethod;
import ferramong.pay.repositories.ToolsPaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ToolsPaymentService {

    private final ToolsPaymentRepository repository;
    private final WalletService walletService;

    public boolean payWithCreditCard(int idDweller, Card card, double value) {
        return payWithCard(idDweller, PaymentMethod.CREDIT_CARD, card, value);
    }

    private boolean payWithCard(int idDweller, PaymentMethod method, Card card, double value) {
        if (!card.pay(value))
            return false;

        return doPayment(idDweller, method, value);
    }

    private boolean doPayment(int idDweller, PaymentMethod method, double value) {
        if (!walletService.hasWallet(idDweller))
            walletService.newWallet(idDweller);

        try {
            ToolsPayment payment = new ToolsPayment(idDweller, method, value);

            repository.save(payment);

            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean payWithDebitCard(int idDweller, Card card, double value) {
        return payWithCard(idDweller, PaymentMethod.DEBIT_CARD, card, value);
    }

    public boolean payWithMoney(int idDweller, double value) {
        return doPayment(idDweller, PaymentMethod.MONEY, value);
    }

    public boolean payWithCreditools(int idDweller, double value) {
        if (!walletService.hasWallet(idDweller))
            walletService.newWallet(idDweller);

        if (walletService.getBalance(idDweller) < value)
            return false;

        if (!walletService.debit(idDweller, value))
            return false;

        return doPayment(idDweller, PaymentMethod.CREDITOOLS, value);
    }

    public List<Payment> getAllDwellerPurchases(int idDweller) {
        return generatePaymentListOf(repository.getAllDwellerPurchases(idDweller));
    }

    public List<Payment> getAllOngPurchases(Date start, Date end) {
        return generatePaymentListOf(repository.getAllOngPurchases(start, end));
    }

    private List<Payment> generatePaymentListOf(List<ToolsPayment> payments) {
        List<Payment> purchases = new ArrayList<>();

        for (ToolsPayment purchase : payments) {
            purchases.add(purchase.getPayment());
        }

        return purchases;
    }
}
