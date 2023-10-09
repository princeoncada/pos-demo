package service

import model.Transaction
import repository.TransactionRepository
import service.base.EntityServiceImpl

class TransactionService(
    transactionRepository: TransactionRepository
): EntityServiceImpl<Transaction, String>(transactionRepository) {}