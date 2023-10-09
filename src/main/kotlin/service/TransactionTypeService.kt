package service

import model.TransactionType
import repository.TransactionTypeRepository
import service.base.EntityServiceImpl

class TransactionTypeService(
    transactionTypeRepository: TransactionTypeRepository
): EntityServiceImpl<TransactionType, String>(transactionTypeRepository) {}