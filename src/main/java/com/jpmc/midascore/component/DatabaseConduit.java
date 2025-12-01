package com.jpmc.midascore.component;

import com.jpmc.midascore.entity.TransactionRecord;
import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Incentive;
import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.repository.TransactionRepository;
import com.jpmc.midascore.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConduit {
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final IncentiveApiService incentiveApiService;

    public DatabaseConduit(UserRepository userRepository, TransactionRepository transactionRepository,  IncentiveApiService incentiveApiService) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.incentiveApiService = incentiveApiService;
    }

    public void save(UserRecord userRecord) {
        userRepository.save(userRecord);
    }

    public void save(Transaction transaction) {
        UserRecord sender = userRepository.findById(transaction.getSenderId());
        UserRecord recipient = userRepository.findById(transaction.getRecipientId());

        if (validate(sender, recipient, transaction)) {
            Incentive incentive = incentiveApiService.getIncentive(transaction);
            float incentiveAmount = incentive.getAmount();
            
            sender.setBalance(sender.getBalance() - transaction.getAmount());
            recipient.setBalance(recipient.getBalance() + transaction.getAmount() + incentiveAmount);

            userRepository.save(sender);
            userRepository.save(recipient);
            transactionRepository.save(new TransactionRecord(sender, recipient, transaction.getAmount(), incentiveAmount));
        }
    }

    private boolean validate(UserRecord sender, UserRecord recipient, Transaction transaction) {
        if (sender == null || recipient == null) return false;
        return !(sender.getBalance() < transaction.getAmount());
    }
}