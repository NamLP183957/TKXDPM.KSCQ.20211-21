package com.sapo.subsystem;


import com.sapo.dto.PaymentTransaction.PaymentTransactionDTO;
import com.sapo.entities.Card;
import com.sapo.entities.PaymentTransaction;
import com.sapo.exception.PaymentException;
import com.sapo.exception.UnrecognizedException;

/**
 * The {@code InterbankInterface} class is used to communicate with the
 * {@link subsystem.InterbankSubsystem InterbankSubsystem} to make transaction
 *
 * @author
 *
 */
public interface InterbankInterface {
    /**
     * Phương thức này dùng để thanh toán
     * @param card      - thẻ của người dùng để thực hiện giao dịch
     * @param amount    - tổng tiền phải trả
     * @param contents  - nội dung giao dịch
     * @return (@link PaymentTransactionDTO) - nếu giao dịch thành cồng
     * @throws PaymentException         - nếu mã trả về là error code
     * @throws UnrecognizedException    - nếu mã lỗi trả về không xác định hoắc có lỗi hệ thống.
     */
    public abstract PaymentTransactionDTO pay(Card card, int amount, String contents)
            throws PaymentException, UnrecognizedException;

    /**
     * Phương thức này dùng để trả lại tiền cho người dùng
     * @param card      - thẻ của người dùng để thực hiện giao dịch
     * @param amount    - tổng tiền trả lại cho người dùng
     * @param contents  - nội dung giao dịch
     * @return (@link PaymentTransaction) - nếu giao dịch thành cồng
     * @throws PaymentException         - nếu mã trả về là error code
     * @throws UnrecognizedException    - nếu mã lỗi trả về không xác định hoắc có lỗi hệ thống.
     */
    public abstract  PaymentTransactionDTO refund(Card card, int amount, String contents)
        throws PaymentException, UnrecognizedException;
}
