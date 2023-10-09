package service

import model.SaleItem
import repository.SaleItemRepository
import service.base.EntityServiceImpl

class SaleItemService(
    saleItemRepository: SaleItemRepository
): EntityServiceImpl<SaleItem, String>(saleItemRepository) {}