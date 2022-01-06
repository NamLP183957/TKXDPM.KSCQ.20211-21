package com.sapo.subsystem;

import com.sapo.dto.PaymentTransaction.PaymentTransactionDTO;
import com.sapo.entities.Card;
import com.sapo.exception.PaymentException;
import com.sapo.exception.UnrecognizedException;
import com.sapo.subsystem.interbank.InterbankSubsystemController;
import org.springframework.stereotype.Component;

/**
 * Class này dùng để liên kết với Interbank để tạo giao dịch
 */
@Component
public class InterbankSubsystem implements InterbankInterface{
    /**
     * Controller của subsystem.
     */
    private InterbankSubsystemController ctrl;

    public InterbankSubsystem() {
        this.ctrl = new InterbankSubsystemController();
    }

    /**
     * Phương thức này dùng để thanh toán tiền cho người dùng
     * @param card      - thẻ của người dùng để thực hiện giao dịch
     * @param amount    - tổng tiền phải trả
     * @param contents  - nội dung giao dịch
     * @return
     * @throws PaymentException
     * @throws UnrecognizedException
     */
    @Override
    public PaymentTransactionDTO pay(Card card, int amount, String contents) throws PaymentException, UnrecognizedException {
        PaymentTransactionDTO transaction = ctrl.pay(card, amount, contents);
        return transaction;
    }

    /**
     * Phương thức này dùng để hoàn trả tiền cho người dùng
     * @param card      - thẻ của người dùng để thực hiện giao dịch
     * @param amount    - tổng tiền trả lại cho người dùng
     * @param contents  - nội dung giao dịch
     * @return
     * @throws PaymentException
     * @throws UnrecognizedException
     */
    @Override
    public PaymentTransactionDTO refund(Card card, int amount, String contents) throws PaymentException, UnrecognizedException {
        PaymentTransactionDTO transaction = ctrl.refund(card, amount, contents);
        return transaction;
    }
}
