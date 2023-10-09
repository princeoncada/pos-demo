package service

import model.Sale
import repository.SaleRepository
import service.base.EntityServiceImpl

class SaleService(
    saleRepository: SaleRepository
): EntityServiceImpl<Sale, String>(saleRepository) {}