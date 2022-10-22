package com.auth.jwt.service;

import com.auth.jwt.model.History;
import com.auth.jwt.model.Order;
import com.auth.jwt.model.Transaction;
import com.auth.jwt.repository.OrderRepo;
import com.auth.jwt.repository.TransactionRepo;
import com.auth.jwt.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepo userRepo;
    private final OrderRepo orderRepo;
    private final TransactionRepo transactionRepo;
    private final HistoryService historyService;

    private final EmailService emailService;

    public void verifyOrder(String transactionNumber, boolean makeTrue){
        var transaction = transactionRepo.findTransactionByOrderNumber(transactionNumber);
        var order = orderRepo.findOrderById(transaction.get().getOrderNumber())
                .orElse(null);
        History history = null;
        if(order != null){
            order.setOrdered(makeTrue);
            history = historyService.saveMyHistory(order.getUser().getId());
            //send email to user that his/her order approved
        }
        orderRepo.save(order);

    }

    public List<Order> getAllOrder(){
        var order = orderRepo.findAllByOrderByCreatedDateAsc();
        return order;
    }

    public List<Transaction> getAllTransaction(){
        return transactionRepo.findAllByOrderByDateAsc();
    }

    public Optional<Order> findOrderNumber(String orderId){
        return orderRepo.findOrderById(orderId);
    }
}